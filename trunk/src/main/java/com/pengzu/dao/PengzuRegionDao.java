package com.pengzu.dao;

import com.pengzu.entity.PengzuRegion;
import com.pengzu.entity.PengzuRegionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PengzuRegionDao {
    long countByExample(PengzuRegionExample example);

    int deleteByExample(PengzuRegionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PengzuRegion record);

    int insertSelective(PengzuRegion record);

    List<PengzuRegion> selectByExample(PengzuRegionExample example);

    PengzuRegion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PengzuRegion record, @Param("example") PengzuRegionExample example);

    int updateByExample(@Param("record") PengzuRegion record, @Param("example") PengzuRegionExample example);

    int updateByPrimaryKeySelective(PengzuRegion record);

    int updateByPrimaryKey(PengzuRegion record);
}