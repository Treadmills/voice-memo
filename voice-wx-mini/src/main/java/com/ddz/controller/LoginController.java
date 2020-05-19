package com.ddz.controller;

import com.alibaba.fastjson.JSONObject;
import com.ddz.common.wx.WxApiHelper;
import com.ddz.domain.entity.ActivityEntity;
import com.ddz.domain.entity.ActivityShareEntity;
import com.ddz.domain.entity.UserEntity;
import com.ddz.domain.entity.UserInfo;
import com.ddz.domain.service.ActivityService;
import com.ddz.domain.service.ActivityShareService;
import com.ddz.domain.service.UserService;
import com.ddz.utils.base.Json2Utils;
import com.ddz.utils.base.R;
import com.ddz.utils.config.FileConfig;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Created by admin on 2019/11/18.
 */
@RestController
@RequestMapping(value = "/auth", produces = "application/json")
public class LoginController extends BaseController{

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityShareService activityShareService;

    @GetMapping(value = "login")
    public R login(HttpServletRequest request, @RequestParam(name = "code",required = true) String code,
                   @RequestParam(name = "userInfo",required = false) String userInfoJson,
                   @RequestParam(name = "cmd",required = false) String cmd){

        if(logger.isDebugEnabled()){
            logger.debug("小程序登录，code=[{}]",code);
        }
        //根据code进行授权实现微信登录功能
        Map<String,Object> resultMap = WxApiHelper.code2Session(code);

        if(!resultMap.containsKey("errcode")){
            // 获取用户openId
            String openId = resultMap.get("openid").toString();
            // 获取用户session_key
            String sessionKey = resultMap.get("session_key").toString();
            System.out.println("授权成功!");
            if(logger.isDebugEnabled()){
                logger.debug("登录的用户，openId=[{}]",openId);
                logger.debug("登录的用户，sessionKey=[{}]",sessionKey);
            }
            HttpSession session = request.getSession();
            //用户信息登录
            UserInfo userInfo = new UserInfo();
            userInfo.setJseessionKey(request.getSession().getId());
            UserEntity userEntity = activity(openId,cmd);

            userEntity = updUserInfo(userInfoJson,openId,userEntity);

            BeanUtils.copyProperties(userEntity, userInfo);
            resultMap.put("userInfo",userInfo);
            session.setAttribute("userInfo", userInfo);
            System.out.println(FileConfig.getPath());
            return R.isOk().data(resultMap);
        }else {
            logger.error("小程序登录，errcode=[{}]",resultMap.get("errcode"));
            return R.isFail();
        }
    }

    public UserEntity activity(String openId,String cmd){

        logger.info("小程序活动，cmd=[{}]",cmd);
        UserEntity newUser = userService.findUserByWxOpenid(openId);
        if(null != cmd && !"".equals(cmd)){
            //SHARE_CMD_ID
            String[] ss = cmd.split("_");
            if(ss.length>=3){
                if(ss[0].equals("SHARE")){
                    if(ss[1].equals("SHARE")){
                        ActivityEntity activityEntity = activityService.findByActivityKey(ss[1]);
                        if(activityEntity!=null){
                            logger.info(JSONObject.toJSONString(activityEntity));
                            UserEntity sharedUser = userService.findUserByUserId(Long.parseLong(ss[2]));
                            //分享者存在，被分享者是新用户
                            if(null != sharedUser && null == newUser){
                                newUser = updUserInfo("",openId,newUser);
                                ActivityShareEntity activityShareEntity = new ActivityShareEntity();
                                activityShareEntity.setActivityId(activityEntity.getActivityId());
                                activityShareEntity.setSharedId(sharedUser.getUserId());
                                activityShareEntity.setUserId(newUser.getUserId());
                                if(null != activityShareService.update(activityShareEntity).getActivityShareId()){
                                    sharedUser.setVoiceTotal(sharedUser.getVoiceTotal()+10);
                                    userService.updateUserEntity(sharedUser);
                                }
                            }else{
                                logger.info("不是新用户，或者老用户不存在！");
                            }
                        }
                    }
                }
            }
        }

        return newUser;
    }

    public UserEntity updUserInfo(String userInfoJson,String wxOpenid,UserEntity userEntity ) {

        //小程序获取用户基本信息
        userEntity = userEntity==null?new UserEntity():userEntity;
        Map<String, Object> map = Json2Utils.toMap(userInfoJson);
        if(map!=null){
            if (map.containsKey("avatarUrl")) {
                userEntity.setAvatar(map.get("avatarUrl").toString());
            }
            if (map.containsKey("nickName")) {
                userEntity.setNickName(map.get("nickName").toString());
            }
            if (map.containsKey("gender")) {
                switch (map.get("gender").toString()) {
                    case "2":
                        userEntity.setSex("0");
                        break;
                    case "1":
                        userEntity.setSex("1");
                        break;
                    case "0":
                        userEntity.setSex("9");
                        break;
                    default:
                        break;
                }
            }
            String address = "";
            if (map.containsKey("province")) {
                address = map.get("province").toString();
            }
            if (map.containsKey("city")) {
                address += map.get("city");
            }
            userEntity.setAddress(address);
        }
        userEntity.setWxOpenid(wxOpenid);
        int ret = userService.updateUserEntity(userEntity);

        return userService.findUserByWxOpenid(wxOpenid);
    }

}
