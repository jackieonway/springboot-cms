package com.pengzu.job;

import com.pengzu.entity.Headline;
import com.pengzu.entity.vo.HeadlineVo;
import com.pengzu.service.HeadlineService;
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
public class HeadlineJob implements Job,Serializable {

    protected static Logger logger = Logger.getLogger(HeadlineJob.class);

    @Autowired
    private HeadlineService headlineService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("轮播图定时器执行开始了..." + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        HeadlineVo headlineVo = new HeadlineVo();
        headlineVo.setStatus(0);
        headlineVo.setPageNum(1);
        headlineVo.setPageSize(1000);
        List<Headline> headlines = headlineService.queryHeadlines(headlineVo);
        if (headlines != null && headlines.size()>0){
            logger.info("未发布轮播图查询到: ["+headlines.size()+"] 个");
            for (Headline headline : headlines) {
                if (headline.getGmtPublish().getTime() <= System.currentTimeMillis()){
                    headline.setStatus(1);
                    headlineService.updateHeadlinePublishState(headline);
                }
            }
        }
        logger.info("轮播图定时器执行结束了..." + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
