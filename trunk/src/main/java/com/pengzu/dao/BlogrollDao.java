package com.pengzu.dao;

import com.pengzu.entity.Blogroll;
import com.pengzu.entity.BlogrollExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogrollDao {
    long countByExample(BlogrollExample example);

    int deleteByExample(BlogrollExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Blogroll record);

    int insertSelective(Blogroll record);

    List<Blogroll> selectByExample(BlogrollExample example);

    Blogroll selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Blogroll record, @Param("example") BlogrollExample example);

    int updateByExample(@Param("record") Blogroll record, @Param("example") BlogrollExample example);

    int updateByPrimaryKeySelective(Blogroll record);

    int updateByPrimaryKey(Blogroll record);
}