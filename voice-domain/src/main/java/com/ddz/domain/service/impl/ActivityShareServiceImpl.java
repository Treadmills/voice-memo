package com.ddz.domain.service.impl;

import com.ddz.domain.entity.ActivityEntity;
import com.ddz.domain.entity.ActivityEntityExample;
import com.ddz.domain.entity.ActivityShareEntity;
import com.ddz.domain.entity.ActivityShareEntityExample;
import com.ddz.domain.mapper.ActivityEntityMapper;
import com.ddz.domain.mapper.ActivityShareEntityMapper;
import com.ddz.domain.service.ActivityShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2020/4/4.
 */
@Service
public class ActivityShareServiceImpl implements ActivityShareService{

    @Resource
    ActivityShareEntityMapper activityShareEntityMapper;

    public ActivityShareEntity findByActivityShareId(Long activityShareId){
        return activityShareEntityMapper.selectByPrimaryKey(activityShareId);
    }

    public List<ActivityShareEntity> findByActivityId(Long activityId){
        ActivityShareEntityExample activityEntityExample = new ActivityShareEntityExample();
        activityEntityExample.createCriteria().andActivityIdEqualTo(activityId);
        List<ActivityShareEntity> activityShareEntities = activityShareEntityMapper.selectByExample(activityEntityExample);
        return activityShareEntities.size()!=0?activityShareEntities: Collections.EMPTY_LIST;
    }

    public List<ActivityShareEntity> findBySharedId(Long shareId){
        ActivityShareEntityExample activityEntityExample = new ActivityShareEntityExample();
        activityEntityExample.createCriteria().andSharedIdEqualTo(shareId);
        List<ActivityShareEntity> activityShareEntities = activityShareEntityMapper.selectByExample(activityEntityExample);
        return activityShareEntities.size()!=0?activityShareEntities: Collections.EMPTY_LIST;
    }

    public List<ActivityShareEntity> findByUserId(Long userId){
        ActivityShareEntityExample activityEntityExample = new ActivityShareEntityExample();
        activityEntityExample.createCriteria().andUserIdEqualTo(userId);
        List<ActivityShareEntity> activityShareEntities = activityShareEntityMapper.selectByExample(activityEntityExample);
        return activityShareEntities.size()!=0?activityShareEntities: Collections.EMPTY_LIST;
    }

    public ActivityShareEntity update (ActivityShareEntity activityShareEntity){
        if(null != activityShareEntity){
            if(null != activityShareEntity.getActivityShareId()){
                activityShareEntityMapper.updateByPrimaryKeySelective(activityShareEntity);
            }else{
                activityShareEntity.setIndate(new Date());
                activityShareEntityMapper.insertSelective(activityShareEntity);
            }
        }
        return activityShareEntity;
    }
}
