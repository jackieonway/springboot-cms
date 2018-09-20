package com.pengzu.service;

import com.pengzu.entity.QuartzEntity;

import java.util.List;

/**
 * @author Jackie
 * @version 1.0
 * @interfaceName JobService
 * @description 定时任务接口
 * @date 2018/9/11 14:51
 **/
public interface JobService {

    /**
     * 查询任务列表
     *
     * @param quartzEntity 查询实体
     * @param pageNum      当前页
     * @param pageSize     每页大小
     * @return 查询结果
     */
    List<QuartzEntity> queryQuartzEntity(QuartzEntity quartzEntity, Integer pageNum, Integer pageSize);

    /**
     * 查询任务数量
     *
     * @param quartz 查询实体
     * @return 数量
     */
    Long queryQuartzEntityCount(QuartzEntity quartz);

    /**
     * 查询任务
     *
     * @param quartzEntity 查询实体
     * @return 查询结果
     */
    QuartzEntity queryQuartzEntityByNameAndGroup(QuartzEntity quartzEntity);
}
