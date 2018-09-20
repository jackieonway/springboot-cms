package com.pengzu.dao;

import com.pengzu.entity.QuartzEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jackie
 * @version 1.0
 * @interfaceName JobDao
 * @description
 * @date 2018/9/11 15:01
 **/
@Repository
public interface JobDao {

    List<QuartzEntity> selectQuartzEntity(@Param("quartz") QuartzEntity quartz, @Param("offset") Integer offset, @Param("rows") Integer rows);

    Long selectQuartzEntityCount(@Param("quartz") QuartzEntity quartz);

    QuartzEntity selectQuartzEntityByJobNameAndJobGroup(@Param("quartz") QuartzEntity quartz);
}
