package com.pengzu.dao;

import com.pengzu.entity.Headline;
import com.pengzu.entity.HeadlineExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadlineDao {
    long countByExample(HeadlineExample example);

    int deleteByExample(HeadlineExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Headline record);

    int insertSelective(Headline record);

    List<Headline> selectByExample(HeadlineExample example);

    Headline selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Headline record, @Param("example") HeadlineExample example);

    int updateByExample(@Param("record") Headline record, @Param("example") HeadlineExample example);

    int updateByPrimaryKeySelective(Headline record);

    int updateByPrimaryKey(Headline record);
}