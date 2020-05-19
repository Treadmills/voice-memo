package com.ddz.controller;

import com.ddz.aop.LoginRequired;
import com.ddz.domain.entity.ActivityShareEntity;
import com.ddz.domain.entity.UserInfo;
import com.ddz.domain.service.ActivityShareService;
import com.ddz.domain.service.UserService;
import com.ddz.utils.base.R;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2020/4/4.
 */
@RestController
@RequestMapping(value = "/personal", produces = "application/json; charset=utf-8")
public class PersonalController extends BaseController{
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ActivityShareService activityShareService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "shared",method = RequestMethod.GET)
    @LoginRequired
    public R list(HttpServletRequest request){
        UserInfo userInfo = getCurrentUserInfo(request);
        List<ActivityShareEntity> list = activityShareService.findBySharedId(userInfo.getUserId());
        Map<String,Object> map = new HashedMap(4);
        map.put("shareCount",list.size());
        if(list.size()>0){
            String avatar1 = userService.findUserByUserId(list.get(0).getUserId()).getAvatar();
            map.put("shareUser1",null == avatar1?"../../utils/img/avatar.png":avatar1);
        }
        if(list.size()>1){
            String avatar2 = userService.findUserByUserId(list.get(1).getUserId()).getAvatar();
            map.put("shareUser2",null == avatar2?"../../utils/img/avatar.png":avatar2);
        }
        if(list.size()>2){
            String avatar3 = userService.findUserByUserId(list.get(2).getUserId()).getAvatar();
            map.put("shareUser3",null == avatar3?"../../utils/img/avatar.png":avatar3);
        }
        return R.isOk().data(map);
    }
}
