package com.pengzu.service.impl;

import com.github.pagehelper.PageHelper;
import com.pengzu.dao.ArticleDao;
import com.pengzu.entity.Article;
import com.pengzu.entity.ArticleExample;
import com.pengzu.entity.vo.ArticleVo;
import com.pengzu.service.ArticleService;
import com.pengzu.utils.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 * @date 2017/12/3
 */
@Service("articleService")
public class ArticleSerivceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article addArticle(ArticleVo articleVo) {
        Date now = new Date();
        if (articleVo.getGmtPublish().getTime() >= System.currentTimeMillis()) {
            articleVo.setIsPublish(0);
        } else {
            articleVo.setIsPublish(1);
        }
        articleVo.setCreateTime(now);
        articleVo.setUpdateTime(now);
        //默认是隐藏
        articleVo.setStatus(1);
        //默认是未审核
        articleVo.setCheck(1);
        //默认1次浏览
        articleVo.setViewCount(1L);
        Article article = BeanConvertUtils.convert(articleVo, Article.class);
        if (articleDao.insertSelective(article) > 0) {
            return article;
        }
        return null;
    }

    @Override
    public Article updateArticle(ArticleVo articleVo) {
        Article article = articleDao.selectByPrimaryKey(articleVo.getId());
        article.setPicture(articleVo.getPicture());
        article.setKeywords(articleVo.getKeywords());
        article.setSummary(articleVo.getSummary());
        article.setTitle(articleVo.getTitle());
        article.setContent(articleVo.getContent());
        if (article.getGmtPublish().getTime() >= System.currentTimeMillis()) {
            article.setIsPublish(0);
        } else {
            article.setIsPublish(1);
        }
        article.setUpdateTime(new Date());
        article.setGmtPublish(articleVo.getGmtPublish());
        article.setGmtArticle(articleVo.getGmtArticle());
        if (articleDao.updateByPrimaryKeyWithBLOBs(article) == 1) {
            return article;
        }
        return null;
    }

    @Override
    public Article queryArticle(ArticleVo article) {
        return articleDao.selectByPrimaryKey(article.getId());
    }

    @Override
    public List<Article> queryArticles(ArticleVo article,List<Long> folderIds) {
        article.setIsDelete(0);
        ArticleExample articleExample = toArticleExample(article, "create_time desc",folderIds);
        PageHelper.startPage(article.getPageNum(), article.getPageSize());
        return articleDao.selectByExample(articleExample);
    }

    @Override
    public Long queryArticleCount(ArticleVo article,List<Long> folderIds) {
        article.setIsDelete(0);
        ArticleExample articleExample = toArticleExample(article, null,folderIds);
        return articleDao.countByExample(articleExample);
    }

    @Override
    public Integer updateViewCountById(ArticleVo articleVo) {
        return articleDao.updateByPrimaryKeySelective(
                BeanConvertUtils.convert(articleVo, Article.class));
    }

    @Override
    public Integer deleteArticle(ArticleVo articleVo) {
        articleVo.setIsDelete(1);
        return articleDao.updateByPrimaryKeySelective(
                BeanConvertUtils.convert(articleVo, Article.class));
    }

    @Override
    public Integer updatePublishState(Article article) {
        return articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    public Integer updateCheckStatus(Article article) {
        return articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    public Integer updateStatus(Article article) {
        return articleDao.updateByPrimaryKeySelective(article);
    }

    private ArticleExample toArticleExample(ArticleVo article, String orderByClause , List<Long> folderIds) {
        ArticleExample articleExample = new ArticleExample();
        if (orderByClause != null) {
            articleExample.setOrderByClause(orderByClause);
        }
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        if (article.getFatherFolderId() != null) {
            criteria.andFatherFolderIdEqualTo(article.getFatherFolderId());
        }
        if (article.getFolderId() != null) {
            criteria.andFolderIdEqualTo(article.getFolderId());
        }
        if (folderIds != null && folderIds.size() > 0){
            criteria.andFolderIdIn(folderIds);
        }
        if (article.getSearch() != null) {
            String search = article.getSearch().replaceAll(" ", "%");
            String[] keys = {"summary", "keywords", "content"};
            criteria.orLike(keys, "%" + search + "%");
        }
        if (article.getCheck() != null) {
            criteria.andCheckEqualTo(article.getCheck());
        }
        if (article.getUserId() != null) {
            criteria.andUserIdEqualTo(article.getUserId());
        }
        if (article.getIsDelete() != null) {
            criteria.andIsDeleteEqualTo(article.getIsDelete());
        }
        if (article.getIsPublish() != null) {
            criteria.andIsPublishEqualTo(article.getIsPublish());
        }
        if (article.getStatus() != null) {
            criteria.andStatusEqualTo(article.getStatus());
        }
        return articleExample;
    }
}
