package com.ddz.controller;

import com.ddz.config.shiro.MD5Utils;
import com.ddz.config.shiro.ShiroUtils;
import com.ddz.utils.base.R;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2020/5/18.
 */
@Api(value = "登录相关")
@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping({ "/", "" })
    @ApiIgnore
    String welcome(Model model) {
        return "redirect:/index";
    }

    @GetMapping({ "/index" })
    @ApiIgnore
    String index(Model model) {
        model.addAttribute("user",ShiroUtils.getUser());
        return "index";
    }

    @GetMapping("/login")
    @ApiIgnore
    String login() {
        return "login";
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录接口",notes = "用户登录时调用",httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(dataType = "string",name = "username",value = "用户名",required = true),
        @ApiImplicitParam(dataType = "string",name = "password",value = "密码",required = true),
        @ApiImplicitParam(dataType = "string",name = "captcha",value = "验证码",required = true)
    })
    @ResponseBody
    public R ajaxLogin(@RequestParam(value = "username",required = true) String username,
                       @RequestParam(value = "password",required = true) String password,
                       @RequestParam(value = "captcha",required = true) String captcha) {
        logger.debug("username:{}",username);
        logger.debug("password:{}",password);
        logger.debug("captcha:{}",captcha);
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            System.out.println(subject.getSession().getAttribute("kaptcha"));
            return R.isOk();
        } catch (AuthenticationException e) {
            return R.isFail("用户或密码错误");
        }
    }

    @GetMapping("/logout")
    @ApiOperation(value = "登出接口",notes = "用户退出时调用",httpMethod = "GET")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    @ApiIgnore
    String main() {
        return "redirect:/druid/index.html";
    }

    @GetMapping("/error")
    @ApiIgnore
    String error() {
        return "error";
    }
}
