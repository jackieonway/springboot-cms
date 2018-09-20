package com.pengzu.job;

import com.pengzu.entity.Article;
import com.pengzu.entity.vo.ArticleVo;
import com.pengzu.service.ArticleService;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HeadlineJob
 * @Description TODO
 * @Author Jackie
 * @Date 2018/9/11 17:17
 * @Version 1.0
 **/
public class ArticleJob implements Job,Serializable {

    protected static Logger logger = Logger.getLogger(ArticleJob.class);

    @Autowired
    private ArticleService articleService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("文章定时器执行开始了..." + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        ArticleVo articleVo = new ArticleVo();
        articleVo.setIsPublish(0);
        articleVo.setPageNum(1);
        articleVo.setPageSize(1000);
        articleVo.setCheck(0);
        articleVo.setStatus(0);
        List<Article> articles = articleService.queryArticles(articleVo,null);
        if (articles != null && articles.size() > 0){
            logger.info("未发布文章查询到: ["+articles.size()+"] 个");
            for (Article article :articles) {
                if (article.getGmtPublish().getTime() <= System.currentTimeMillis()){
                    article.setIsPublish(1);
                    articleService.updatePublishState(article);
                }
            }
        }
        logger.info("文章定时器执行结束了..." + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
