package com.pengzu.schedule;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * Quartz 分布式定时任务配置
 */
@Configuration
public class SchedulerConfig {

    private Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

	@Autowired
	private SpringJobFactory springJobFactory;

	@Autowired
    private ConfigurableApplicationContext context;

    @Bean(name="schedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setAutoStartup(true);
        factory.setStartupDelay(5);//延时5秒启动
        factory.setQuartzProperties(quartzProperties());
        factory.setJobFactory(springJobFactory);  
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        String[] activeProfiles =  context.getEnvironment().getActiveProfiles();
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        if (activeProfiles != null && activeProfiles.length > 0 ){
            if (activeProfiles.length == 1){
                propertiesFactoryBean.setLocation(new ClassPathResource("/quartz-"+activeProfiles[0]+".properties"));
            }else {
                logger.error("There is too many activeProfiles..");
            }
        }
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /*
     * quartz初始化监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
       return new QuartzInitializerListener();
    }

    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name="scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}