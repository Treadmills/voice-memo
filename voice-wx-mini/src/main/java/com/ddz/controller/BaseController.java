package com.ddz.controller;

import com.ddz.domain.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2019/11/20.
 */
public class BaseController {

    /**
     * 获取当前用户状态
     *
     * @param request
     * @return
     */
    public UserInfo getCurrentUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (UserInfo) session.getAttribute("userInfo");
    }

    /**
     * 移除当前用户登录态
     *
     * @param request
     * @return
     */
    public void removeCurrentUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userInfo");
    }
}
