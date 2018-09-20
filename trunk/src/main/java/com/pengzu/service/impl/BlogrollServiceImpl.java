package com.pengzu.service.impl;

import com.github.pagehelper.PageHelper;
import com.pengzu.dao.BlogrollDao;
import com.pengzu.entity.Blogroll;
import com.pengzu.entity.BlogrollExample;
import com.pengzu.entity.vo.BlogRollVo;
import com.pengzu.service.BlogRollService;
import com.pengzu.utils.BeanConvertUtils;
import com.pengzu.utils.RegExpValidatorUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("blogrollService")
public class BlogrollServiceImpl implements BlogRollService {

    private static Logger logger = Logger.getLogger(BlogrollServiceImpl.class);

    @Autowired
    private BlogrollDao blogrollDao;

    @Override
    public Integer addBlogroll(BlogRollVo blogrollVo) {
        try {
            String url = blogrollVo.getBlogrollUrl();
            if (!(RegExpValidatorUtils.isUrl(url) || RegExpValidatorUtils.isIP(url))) {
                logger.info("链接地址有误....!");
                return 0;
            }
            Date now = new Date();
            blogrollVo.setCreateDate(now);
            blogrollVo.setModifyDate(now);
            Blogroll blogroll = BeanConvertUtils.convert(blogrollVo, Blogroll.class);
            return blogrollDao.insertSelective(blogroll);
        } catch (Exception e) {
            logger.error("新增友情链接失败!", e);
            return 0;
        }
    }

    @Override
    public Integer updateBlogroll(BlogRollVo blogrollVo) {
        try {
            String url = blogrollVo.getBlogrollUrl();
            if (!(RegExpValidatorUtils.isUrl(url) || RegExpValidatorUtils.isIP(url))) {
                logger.info("链接地址有误....!");
                return 0;
            }
            blogrollVo.setModifyDate(new Date());
            Blogroll blogroll = BeanConvertUtils.convert(blogrollVo, Blogroll.class);
            return blogrollDao.updateByPrimaryKeySelective(blogroll);
        } catch (Exception e) {
            logger.error("更新友情链接失败!", e);
            return 0;
        }
    }

    @Override
    public Blogroll queryBlogrollByPrimaryKey(Long id) {
        try {
            return blogrollDao.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("查询友情链接失败!", e);
            return null;
        }
    }

    @Override
    public Blogroll queryBlogroll(BlogRollVo blogRollVo) {
        BlogrollExample blogrollExample = new BlogrollExample();
        BlogrollExample.Criteria criteria = blogrollExample.createCriteria();
        criteria.andBlogrollImageUrlLike(blogRollVo.getBlogrollUrl());
        try {
            return blogrollDao.selectByExample(blogrollExample).get(0);
        } catch (Exception e) {
            logger.error("查询友情链接失败!", e);
            return null;
        }
    }

    @Override
    public List<Blogroll> queryBlogrolls(BlogRollVo blogRollVo) {
        BlogrollExample blogrollExample = new BlogrollExample();
        BlogrollExample.Criteria criteria = blogrollExample.createCriteria();
        criteria.andStatusEqualTo(0);
        PageHelper.startPage(blogRollVo.getPageNum(), blogRollVo.getPageSize());
        try {
            return blogrollDao.selectByExample(blogrollExample);
        } catch (Exception e) {
            logger.error("查询友情链接失败!", e);
            return null;
        }
    }

    @Override
    public Long queryBlogrollsCount(BlogRollVo blogRollVo) {
        BlogrollExample blogrollExample = new BlogrollExample();
        BlogrollExample.Criteria criteria = blogrollExample.createCriteria();
        criteria.andStatusEqualTo(0);
        try {
            return blogrollDao.countByExample(blogrollExample);
        } catch (Exception e) {
            logger.error("查询友情链接数量失败!", e);
            return 0L;
        }
    }

    @Override
    public Integer deleteBlogrollByPrimaryKey(Long id) {
        try {
            Blogroll blogroll = new Blogroll();
            blogroll.setId(id);
            blogroll.setStatus(1);
            return blogrollDao.updateByPrimaryKeySelective(blogroll);
        } catch (Exception e) {
            logger.error("删除友情链接数量失败!", e);
            return 0;
        }
    }
}
