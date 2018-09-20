package com.pengzu.dao;

import com.pengzu.entity.PengzuConfig;
import com.pengzu.entity.PengzuConfigExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PengzuConfigDao {
    long countByExample(PengzuConfigExample example);

    int deleteByExample(PengzuConfigExample example);

    int deleteByPrimaryKey(String key);

    int insert(PengzuConfig record);

    int insertSelective(PengzuConfig record);

    List<PengzuConfig> selectByExample(PengzuConfigExample example);

    PengzuConfig selectByPrimaryKey(String key);

    int updateByExampleSelective(@Param("record") PengzuConfig record, @Param("example") PengzuConfigExample example);

    int updateByExample(@Param("record") PengzuConfig record, @Param("example") PengzuConfigExample example);

    int updateByPrimaryKeySelective(PengzuConfig record);

    int updateByPrimaryKey(PengzuConfig record);
}