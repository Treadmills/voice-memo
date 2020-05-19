package com.ddz.domain.service.impl;

import com.ddz.domain.entity.UserEntity;
import com.ddz.domain.entity.UserEntityExample;
import com.ddz.domain.mapper.UserEntityMapper;
import com.ddz.domain.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2019/11/20.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserEntityMapper userEntityMapper;

    @Override
    public UserEntity findUserByWxOpenid(String wxOpenid) {
        UserEntityExample example = new UserEntityExample();
        UserEntityExample.Criteria criteria = example.createCriteria();
        criteria.andWxOpenidEqualTo(wxOpenid);
        List<UserEntity> userEntities = userEntityMapper.selectByExample(example);
        return userEntities.size()>0?userEntities.get(0):null;
    }

    @Override
    public UserEntity findUserByUserId(Long userId) {
        return userEntityMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateUserEntity(UserEntity userEntity) {
        if(null == userEntity.getUserId()){
            userEntity.setIndate(new Date());
            return userEntityMapper.insertSelective(userEntity);
        }else{
            return userEntityMapper.updateByPrimaryKeySelective(userEntity);
        }
    }


}
