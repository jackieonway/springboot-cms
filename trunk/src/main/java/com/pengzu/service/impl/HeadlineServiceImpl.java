package com.pengzu.service.impl;

import com.github.pagehelper.PageHelper;
import com.pengzu.dao.HeadlineDao;
import com.pengzu.entity.Headline;
import com.pengzu.entity.HeadlineExample;
import com.pengzu.entity.vo.HeadlineVo;
import com.pengzu.service.HeadlineService;
import com.pengzu.utils.BeanConvertUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */
@Service("headlineService")
public class HeadlineServiceImpl implements HeadlineService {

    private static Logger logger = Logger.getLogger(HeadlineServiceImpl.class);

    @Autowired
    private HeadlineDao headlineDao;


    @Override
    public Headline addHeadline(HeadlineVo headline) {
        Date now = new Date();
        if (headline.getGmtPublish().getTime() >= System.currentTimeMillis()) {
            headline.setStatus(0);
        } else {
            headline.setStatus(1);
        }
        headline.setGmtCreate(now);
        headline.setGmtModify(now);
        headline.setFile(null);//防止json转化异常
        Headline headline1 = BeanConvertUtils.convert(headline, Headline.class);
        if (headlineDao.insertSelective(headline1) > 0) {
            return headline1;
        }
        return null;
    }

    @Override
    public Headline updateHeadline(HeadlineVo headlineVo) {
        try {
            Headline headline = headlineDao.selectByPrimaryKey(headlineVo.getId());
            headline.setPicture(headlineVo.getPicture());
            headline.setUrl(headlineVo.getUrl());
            headline.setName(headlineVo.getName());
            headline.setAlt(headlineVo.getAlt());
            headline.setGmtModify(new Date());
            if (headlineDao.updateByPrimaryKey(headline) == 1) {
                return headline;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("修改轮播图 [ " + headlineVo.getId() + " ] 失败", e);
            return null;
        }
    }

    @Override
    public Headline queryHeadline(HeadlineVo headlineVo) {
        try {
            return headlineDao.selectByPrimaryKey(headlineVo.getId());
        } catch (Exception e) {
            logger.error("查询轮播图[" + headlineVo.getId() + "]失败", e);
            return null;
        }
    }

    @Override
    public List<Headline> queryHeadlines(HeadlineVo headlineVo) {
        try {
            headlineVo.setIsDelete(0);
            HeadlineExample headlineExample = toHeadlineExample(headlineVo, "gmt_create desc");
            PageHelper.startPage(headlineVo.getPageNum(), headlineVo.getPageSize());
            return headlineDao.selectByExample(headlineExample);
        } catch (Exception e) {
            logger.error("查询轮播图列表失败", e);
            return null;
        }
    }

    @Override
    public Long queryHeadlineCount(HeadlineVo headlineVo) {
        try {
            headlineVo.setIsDelete(0);
            HeadlineExample headlineExample = toHeadlineExample(headlineVo, null);
            return headlineDao.countByExample(headlineExample);
        } catch (Exception e) {
            logger.error("查询轮播图数量失败", e);
            return null;
        }
    }

    @Override
    public Integer deleteHeadline(HeadlineVo headlineVo) {
        try {
            headlineVo.setIsDelete(1);
            return headlineDao.updateByPrimaryKeySelective(
                    BeanConvertUtils.convert(headlineVo, Headline.class));
        } catch (Exception e) {
            logger.error("删除轮播图 [" + headlineVo.getId() + "]失败", e);
            return 0;
        }
    }

    @Override
    public Integer updateHeadlinePublishState(Headline headline) {
        return headlineDao.updateByPrimaryKeySelective(headline);
    }

    private HeadlineExample toHeadlineExample(HeadlineVo headlineVo, String orderByClause) {
        HeadlineExample example = new HeadlineExample();
        if (orderByClause != null) {
            example.setOrderByClause(orderByClause);
        }
        HeadlineExample.Criteria criteria = example.createCriteria();
        if (headlineVo.getIsDelete() != null) {
            criteria.andIsDeleteEqualTo(headlineVo.getIsDelete());
        }
        if (headlineVo.getStatus() != null) {
            criteria.andStatusEqualTo(headlineVo.getStatus());
        }
        return example;
    }
}
