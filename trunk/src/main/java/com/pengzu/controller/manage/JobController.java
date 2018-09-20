package com.pengzu.controller.manage;

import com.pengzu.config.CodeEnum;
import com.pengzu.entity.QuartzEntity;
import com.pengzu.entity.result.Response;
import com.pengzu.service.JobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/adminManager/job")
public class JobController {
    private final static Logger logger = LoggerFactory.getLogger(JobController.class);


    @Autowired
    @Qualifier("scheduler")
    private Scheduler scheduler;

    @Autowired
    private JobService jobService;

    @ResponseBody
    @PostMapping("/add")
    @RequiresPermissions("J03")
    public Response save(QuartzEntity quartz) {
        logger.info("新增任务");
        try {
            //获取Scheduler实例、废弃、使用自动注入的scheduler、否则spring的service将无法注入
            //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //如果是修改  展示旧的 任务
            if (quartz.getOldJobGroup() != null) {
                JobKey key = new JobKey(quartz.getOldJobName(), quartz.getOldJobGroup());
                scheduler.deleteJob(key);
            }
            Class cls = Class.forName(quartz.getJobClassName());
            cls.newInstance();
            //构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
                    quartz.getJobGroup())
                    .withDescription(quartz.getDescription()).build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
                    .startNow().withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(true, CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
        }
        return new Response(true, CodeEnum.SUCCESS.getValue(), null, CodeEnum.SUCCESS_CODE.getValue());
    }

    @GetMapping("/list.html")
    @RequiresPermissions("J02")
    public String list(QuartzEntity quartz, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
            , @RequestParam(value = "pageSize", defaultValue = "12") Integer pageSize, ModelMap modelMap) {
        logger.info("任务列表");
        List<QuartzEntity> list = jobService.queryQuartzEntity(quartz, pageNum, pageSize);
        Long total = jobService.queryQuartzEntityCount(quartz);
        modelMap.put("jobs", list);
        modelMap.put("total", total);
        modelMap.put("pageNum", pageNum);
        modelMap.put("pageSize", pageSize);
        return "manage/job/list";
    }

    @ResponseBody
    @PostMapping("/trigger")
    @RequiresPermissions("J04")
    public Response trigger(QuartzEntity quartz, HttpServletResponse response) {
        try {
            JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
            scheduler.triggerJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return new Response<>(true, CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
        }
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), null, CodeEnum.SUCCESS_CODE.getValue());
    }

    @ResponseBody
    @PostMapping("/pause")
    @RequiresPermissions("J05")
    public Response pause(QuartzEntity quartz, HttpServletResponse response) {
        logger.info("停止任务");
        try {
            JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return new Response<>(true, CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
        }
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), null, CodeEnum.SUCCESS_CODE.getValue());
    }

    @ResponseBody
    @PostMapping("/resume")
    @RequiresPermissions("J06")
    public Response resume(QuartzEntity quartz, HttpServletResponse response) {
        logger.info("恢复任务");
        try {
            JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
            scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return new Response<>(true, CodeEnum.SUCCESS.getValue(), CodeEnum.SUCCESS_CODE.getValue());
        }
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), null, CodeEnum.SUCCESS_CODE.getValue());
    }

    @ResponseBody
    @PostMapping("/remove")
    @RequiresPermissions("J07")
    public Response remove(QuartzEntity quartz, HttpServletResponse response) {
        try {

            TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
            // 停止触发器  
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器  
            scheduler.unscheduleJob(triggerKey);
            // 删除任务  
            scheduler.deleteJob(JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup()));
            System.out.println("removeJob:" + JobKey.jobKey(quartz.getJobName()));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(true, CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
        }
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), null, CodeEnum.SUCCESS_CODE.getValue());
    }

    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("J08")
    public Response update(QuartzEntity quartz, HttpServletResponse response) {
        logger.info("编辑任务");
        try {
            QuartzEntity quartzEntity = jobService.queryQuartzEntityByNameAndGroup(quartz);
            return new Response<>(true, CodeEnum.SUCCESS.getValue(), quartzEntity, CodeEnum.SUCCESS_CODE.getValue());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(true, CodeEnum.SUCCESS.getValue(), CodeEnum.SUCCESS_CODE.getValue());
        }
    }
}
