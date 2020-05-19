package com.ddz.domain.service;

import com.ddz.domain.entity.ActivityShareEntity;

import java.util.List;

/**
 * Created by admin on 2020/4/4.
 */
public interface ActivityShareService {
    ActivityShareEntity findByActivityShareId(Long activityShareId);

    List<ActivityShareEntity> findByActivityId(Long activityId);

    List<ActivityShareEntity> findBySharedId(Long shareId);

    List<ActivityShareEntity> findByUserId(Long userId);

    ActivityShareEntity update (ActivityShareEntity activityShareEntity);


}
