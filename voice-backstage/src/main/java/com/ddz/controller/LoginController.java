package com.ddz.controller;

import com.ddz.config.shiro.ShiroUtils;
import com.ddz.utils.base.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 2020/5/18.
 */
@Controller
public class LoginController {

    @GetMapping({ "/", "" })
    String welcome(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password) {
     //   password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.isOk();
        } catch (AuthenticationException e) {
            return R.isFail("用户或密码错误");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    String main() {
        return "redirect:/druid/index.html";
    }

    @GetMapping("/403")
    String error403() {
        return "403";
    }
}
