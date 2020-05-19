package com.ddz.domain.service;

import com.ddz.domain.entity.ActivityEntity;

import java.util.List;

/**
 * Created by admin on 2020/4/4.
 */
public interface ActivityService {

    ActivityEntity findByActivityId(Long activityId);

    List<ActivityEntity> findListByName(String activityName);

    ActivityEntity findByActivityKey(String key);

    ActivityEntity update(ActivityEntity activityEntity);

    int delete(Long activityId);
}
