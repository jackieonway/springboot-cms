package com.pengzu.dao;

import com.pengzu.entity.Guestbook;
import com.pengzu.entity.GuestbookExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestbookDao {
    long countByExample(GuestbookExample example);

    int deleteByExample(GuestbookExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Guestbook record);

    int insertSelective(Guestbook record);

    List<Guestbook> selectByExample(GuestbookExample example);

    Guestbook selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Guestbook record, @Param("example") GuestbookExample example);

    int updateByExample(@Param("record") Guestbook record, @Param("example") GuestbookExample example);

    int updateByPrimaryKeySelective(Guestbook record);

    int updateByPrimaryKey(Guestbook record);
}