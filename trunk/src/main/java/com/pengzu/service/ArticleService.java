package com.pengzu.service;

import com.pengzu.entity.Article;
import com.pengzu.entity.vo.ArticleVo;

import java.util.List;

public interface ArticleService {

    Article addArticle(ArticleVo articleVo);

    Article updateArticle(ArticleVo articleVo);

    Article queryArticle(ArticleVo articleVo);

    List<Article> queryArticles(ArticleVo articleVo,List<Long> folderIds);

    Long queryArticleCount(ArticleVo articleVo,List<Long> folderIds);

    Integer updateViewCountById(ArticleVo articleVo);

    Integer deleteArticle(ArticleVo articleVo);

    /**
     * 更新文章发布状态
     *
     * @param article 文章实体
     * @return 更新结果
     */
    Integer updatePublishState(Article article);

    /**
     * 更新文章审核状态
     * @param article 待审核文章
     * @return 结果
     */
    Integer updateCheckStatus(Article article);

    /**
     * 更新文章显示状态
     * @param article
     * @return
     */
    Integer updateStatus(Article article);
}
