package com.ddz.controller;

import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.speech.AipSpeech;
import com.ddz.aop.LoginRequired;
import com.ddz.domain.entity.UserEntity;
import com.ddz.domain.entity.UserInfo;
import com.ddz.domain.entity.UserVoiceEntity;
import com.ddz.domain.service.UserService;
import com.ddz.domain.service.UserVoiceService;
import com.ddz.utils.base.DateUtils;
import com.ddz.utils.base.R;
import com.ddz.utils.voice.AudioUtils;
import com.ddz.utils.voice.VoiceAipSpeech;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by admin on 2019/11/20.
 */
@RestController
@RequestMapping(value = "/voice", produces = "application/json; charset=utf-8")
public class VoiceController extends BaseController{

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserVoiceService userVoiceService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @LoginRequired
    public R list(HttpServletRequest request){
        UserInfo userInfo = getCurrentUserInfo(request);

        Map<String,Object> map = new HashedMap(3);
        map.put("list",userVoiceService.getListByUserId(userInfo.getUserId()));
        map.put("voiceTotal",userInfo.getVoiceTotal());
        map.put("voiceCount",userInfo.getVoiceCount());
        return R.isOk().data(map);
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    @LoginRequired
    public R addVoice(HttpServletRequest request, @RequestParam(name = "voiceStr",required = true) String voiceStr
            , @RequestParam(name = "voicePath",required = true) String voicePath){

        UserInfo userInfo = getCurrentUserInfo(request);

        UserVoiceEntity userVoiceEntity = new UserVoiceEntity();
        userVoiceEntity.setUserId(userInfo.getUserId());
        userVoiceEntity.setVoiceMessage(voiceStr);
        userVoiceEntity.setVoicePath(voicePath);
        userVoiceService.updateVoice(userVoiceEntity);
        updateUserVoiceCount(request);
        return R.isOk();
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    @LoginRequired
    public R delete(HttpServletRequest request, @RequestParam(name = "voiceId",required = true) Long voiceId){
        if(userVoiceService.findVoiceByUserVoiceId(voiceId)==null){
            return R.isOk();
        }
        if(userVoiceService.delete(voiceId)>0){
            updateUserVoiceCount(request);
            return R.isOk();
        }else{
            return R.isFail();
        }
    }

    public void updateUserVoiceCount(HttpServletRequest request){
        UserInfo userInfo = getCurrentUserInfo(request);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userInfo.getUserId());
        //需要自己写sql语句
        userEntity.setVoiceCount(userVoiceService.getCountByUserId(userInfo.getUserId()));
        userService.updateUserEntity(userEntity);
        HttpSession session = request.getSession();
        userInfo.setVoiceCount(userEntity.getVoiceCount());
        session.setAttribute("userInfo",userInfo);
    }
}
