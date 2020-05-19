package com.ddz.domain.service;

import com.ddz.domain.entity.UserEntity;

/**
 * Created by admin on 2019/11/20.
 */
public interface UserService {

    UserEntity findUserByWxOpenid(String wxOpenid);

    UserEntity findUserByUserId(Long userId);

    int updateUserEntity(UserEntity userEntity);
}
