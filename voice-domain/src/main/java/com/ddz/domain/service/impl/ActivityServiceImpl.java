package com.ddz.domain.service.impl;

import com.ddz.domain.entity.ActivityEntity;
import com.ddz.domain.entity.ActivityEntityExample;
import com.ddz.domain.mapper.ActivityEntityMapper;
import com.ddz.domain.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2020/4/4.
 */
@Service
public class ActivityServiceImpl implements ActivityService{

    @Resource
    ActivityEntityMapper activityEntityMapper;

    public ActivityEntity findByActivityId(Long activityId){
        return activityEntityMapper.selectByPrimaryKey(activityId);
    }

    public List<ActivityEntity> findListByName(String activityName){
        ActivityEntityExample activityEntityExample = new ActivityEntityExample();
        activityEntityExample.createCriteria().andActivityNameLike(activityName).andIsDeleteEqualTo(0);
        List<ActivityEntity> activityEntities = activityEntityMapper.selectByExample(activityEntityExample);
        return activityEntities.size()!=0?activityEntities: Collections.EMPTY_LIST;
    }

    public ActivityEntity findByActivityKey(String key){
        ActivityEntityExample activityEntityExample = new ActivityEntityExample();
        activityEntityExample.createCriteria().andActivityKeyEqualTo(key).andIsDeleteEqualTo(0);
        List<ActivityEntity> activityEntities = activityEntityMapper.selectByExample(activityEntityExample);
        return activityEntities.size()>0?activityEntities.get(0):null;
    }

    public ActivityEntity update(ActivityEntity activityEntity){
        if(null != activityEntity){
            if(null != activityEntity.getActivityId()){
                 activityEntityMapper.updateByPrimaryKeySelective(activityEntity);
            }else{
                activityEntity.setIndate(new Date());
                activityEntityMapper.insert(activityEntity);
            }
        }
        return activityEntity;
    }

    public int delete(Long activityId){
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityId(activityId);
        activityEntity.setIsDelete(1);
        return activityEntityMapper.updateByPrimaryKeySelective(activityEntity);
    }
}
