package com.pengzu.service;

import com.pengzu.entity.Blogroll;
import com.pengzu.entity.vo.BlogRollVo;

import java.util.List;

public interface BlogRollService {

    Integer addBlogroll(BlogRollVo blogRollVo);

    Integer updateBlogroll(BlogRollVo blogRollVo);

    Blogroll queryBlogrollByPrimaryKey(Long id);

    Blogroll queryBlogroll(BlogRollVo blogRollVo);

    List<Blogroll> queryBlogrolls(BlogRollVo blogRollVo);

    Long queryBlogrollsCount(BlogRollVo blogRollVo);

    Integer deleteBlogrollByPrimaryKey(Long id);
}
