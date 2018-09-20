package com.pengzu.service.impl;

import com.pengzu.dao.JobDao;
import com.pengzu.entity.QuartzEntity;
import com.pengzu.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jackie
 * @version 1.0
 * @className JobServiceImpl
 * @description 定时任务相关接口实现类
 * @date 2018/9/11 14:53
 **/
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Override
    public List<QuartzEntity> queryQuartzEntity(QuartzEntity quartz, Integer pageNum, Integer pageSize) {
        Integer offset = (pageNum - 1) * pageSize;
        return jobDao.selectQuartzEntity(quartz, offset, pageSize);
    }

    @Override
    public Long queryQuartzEntityCount(QuartzEntity quartz) {
        return jobDao.selectQuartzEntityCount(quartz);
    }

    @Override
    public QuartzEntity queryQuartzEntityByNameAndGroup(QuartzEntity quartzEntity) {
        return jobDao.selectQuartzEntityByJobNameAndJobGroup(quartzEntity);
    }
}
