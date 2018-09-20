package com.pengzu.dao;

import com.pengzu.entity.DownloadFile;
import com.pengzu.entity.DownloadFileExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadFileDao {
    long countByExample(DownloadFileExample example);

    int deleteByExample(DownloadFileExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DownloadFile record);

    int insertSelective(DownloadFile record);

    List<DownloadFile> selectByExample(DownloadFileExample example);

    DownloadFile selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DownloadFile record, @Param("example") DownloadFileExample example);

    int updateByExample(@Param("record") DownloadFile record, @Param("example") DownloadFileExample example);

    int updateByPrimaryKeySelective(DownloadFile record);

    int updateByPrimaryKey(DownloadFile record);
}