package com.ddz.domain.service;

import com.ddz.domain.entity.UserVoiceEntity;

import java.util.List;

/**
 * Created by admin on 2019/11/25.
 */
public interface UserVoiceService {

    List<UserVoiceEntity> getListByUserId(Long userId);

    Long getCountByUserId(Long userId);

    UserVoiceEntity findVoiceByUserVoiceId(Long voiceId);

    UserVoiceEntity updateVoice(UserVoiceEntity userVoiceEntity);

    int delete(Long voiceId);
}
