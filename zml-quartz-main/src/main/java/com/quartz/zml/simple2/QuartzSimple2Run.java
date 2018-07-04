package com.quartz.zml.simple2;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * JobDetail的dataMap传递参数
 * @author zml
 * @date 2018/7/4
 */
public class QuartzSimple2Run {
    public static void main(String[] args) {
        try{
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger_sayhello","group_sayhello")
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()//使用SimpleTrigger
                                    .withIntervalInSeconds(5)//每隔5秒执行一次
                                    .withRepeatCount(5)) //次数为5次+启动时执行一次
                    .build();
            //定义JobDetail
            JobDetail job = JobBuilder.newJob(SayHelloAndWeatherJob.class)//将做业务的自定义定义Job类明确JobDetail
                    .withIdentity("job_sayhello", "group_sayhello")
                    .build();


            job.getJobDataMap().put("weather", "hot"); //加入属性weather到JobDataMap
            job.getJobDataMap().put("name", "zml");

            //加入调度
            scheduler.scheduleJob(job, trigger);//绑定job和trigger到scheduler
            scheduler.start();
            System.out.println("start sayhello with weather job..."+new Date());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}