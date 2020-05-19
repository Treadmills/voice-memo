package com.ddz.domain.service.impl;

import com.ddz.domain.entity.UserVoiceEntity;
import com.ddz.domain.entity.UserVoiceEntityExample;
import com.ddz.domain.mapper.UserVoiceEntityMapper;
import com.ddz.domain.service.UserVoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2019/11/25.
 */
@Service
public class UserVoiceServiceImpl implements UserVoiceService {

    @Resource
    private UserVoiceEntityMapper userVoiceEntityMapper;

    @Override
    public List<UserVoiceEntity> getListByUserId(Long userId) {
        UserVoiceEntityExample example = new UserVoiceEntityExample();
        example.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo(0);
        example.setOrderByClause("F_INDATE DESC");
        List<UserVoiceEntity> list = userVoiceEntityMapper.selectByExample(example);
        return list;
    }

    @Override
    public Long getCountByUserId(Long userId) {
        UserVoiceEntityExample example = new UserVoiceEntityExample();
        example.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo(0);
        return  userVoiceEntityMapper.countByExample(example);
    }

    @Override
    public UserVoiceEntity findVoiceByUserVoiceId(Long voiceId) {
        UserVoiceEntityExample example = new UserVoiceEntityExample();
        example.createCriteria().andUserVoiceIdEqualTo(voiceId);
        List<UserVoiceEntity> list = userVoiceEntityMapper.selectByExample(example);
        return list.size()>0?list.get(0):null;
    }

    @Override
    public UserVoiceEntity updateVoice(UserVoiceEntity userVoiceEntity) {
        if(userVoiceEntity.getUserVoiceId()!=null){
            userVoiceEntityMapper.updateByPrimaryKey(userVoiceEntity);
        }else{
            userVoiceEntity.setIndate(new Date());
            userVoiceEntity.setIsDelete(0);
            userVoiceEntityMapper.insert(userVoiceEntity);
        }
        return userVoiceEntity;
    }

    @Override
    public int delete(Long voiceId) {
        UserVoiceEntity userVoiceEntity = new UserVoiceEntity();
        userVoiceEntity.setIsDelete(1);
        userVoiceEntity.setUserVoiceId(voiceId);
        return userVoiceEntityMapper.updateByPrimaryKeySelective(userVoiceEntity);
    }
}
