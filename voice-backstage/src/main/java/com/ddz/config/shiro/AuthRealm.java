package com.ddz.config.shiro;

import com.ddz.config.AccountInfo;
import com.ddz.config.RoleInfo;
import com.ddz.domain.entity.AccountEntity;
import com.ddz.domain.entity.MenuEntity;
import com.ddz.domain.entity.RoleEntity;
import com.ddz.domain.entity.UserEntity;
import com.ddz.domain.service.AccountService;
import com.ddz.domain.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2020/5/18.
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    /*
     * 真实授权抽象方法，供子类调用
     *
     * 这个是当登陆成功之后会被调用，看当前的登陆角色是有有权限来进行操作
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("doGetAuthorizationInfo方法");
        AccountInfo user = (AccountInfo) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> menuList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        Set<RoleInfo> roleSet = user.getRoles();//拿到角色
        if (CollectionUtils.isNotEmpty(roleSet)) {
            for(RoleInfo role : roleSet) {
                roleNameList.add(role.getRoleName());//拿到角色
                Set<MenuEntity> menuSet = role.getMenus();
                if (CollectionUtils.isNotEmpty(menuSet)) {
                    for (MenuEntity menu : menuSet) {
                        menuList.add(menu.getMenuName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(menuList);//拿到权限
        info.addRoles(roleNameList);//拿到角色
        return info;
    }

    /*
     * 用于认证登录，认证接口实现方法，该方法的回调一般是通过subject.login(token)方法来实现的
     * AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）：
     * AuthenticationInfo是包含了用户根据username返回的数据信息，用于在匹马比较的时候进行相互比较
     *
     * shiro的核心是java servlet规范中的filter，通过配置拦截器，使用拦截器链来拦截请求，如果允许访问，则通过。
     * 通常情况下，系统的登录、退出会配置拦截器。登录的时候，调用subject.login(token),token是用户验证信息，
     * 这个时候会在Realm中doGetAuthenticationInfo方法中进行认证。这个时候会把用户提交的验证信息与数据库中存储的认证信息，将所有的数据拿到，在匹配器中进行比较
     * 这边是我们自己实现的CredentialMatcher类的doCredentialsMatch方法，返回true则一致，false则登陆失败
     * 退出的时候，调用subject.logout()，会清除回话信息
     *
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("将用户，密码填充完UsernamePasswordToken之后，进行subject.login(token)之后");
        UsernamePasswordToken userpasswordToken = (UsernamePasswordToken) token;//这边是界面的登陆数据，将数据封装成token
        String username = userpasswordToken.getUsername();
        AccountEntity accountEntity = accountService.findByUserName(username);
        AccountInfo accountInfo = new AccountInfo();
        BeanUtils.copyProperties(accountEntity, accountInfo);
        return new SimpleAuthenticationInfo(accountInfo,accountInfo.getAccountPassword(),this.getClass().getName());
    }
}
