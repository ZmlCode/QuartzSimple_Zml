package com.quartz.zml.simple1;

import com.quartz.zml.simple1.SayHelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author zml
 * @date 2018/7/4
 */
public class QuartzSimple1Run {
    public static void main(String[] args) {
        try{
            //创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //定义Trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger_sayhello","group_sayhello")
                    .startNow()
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()//使用SimpleTrigger
                                    .withIntervalInSeconds(5)//每隔5秒执行一次
                                    .repeatForever())//一直执行 //  .withRepeatCount(10)) //次数为10次
                    .build();
            //定义JobDetail
            JobDetail job = JobBuilder.newJob(SayHelloJob.class)//将做业务的自定义定义Job类明确JobDetail
                    .withIdentity("job_sayhello", "group_sayhello")
                    .usingJobData("age", "18")//定义属性
                    .build();

            //job.getJobDataMap().put("name", "quertz"); //加入属性name到JobDataMap


            //加入调度
            scheduler.scheduleJob(job, trigger);//绑定job和trigger到scheduler
            //启动
            scheduler.start();
            System.out.println("start sayhello job..."+new Date());

            //所有的调度都是由scheduler控制 提
            // 供各种方法
            //例如  scheduleJob checkExists pauseTrigger unscheduleJob
            //      triggerJob getTriggerState resumeJob（恢复暂停的任务） rescheduleJob
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
        /*DSL fluent interface风格 import static 类的*
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
        JobDetail job = newJob(HelloQuartz.class)
        */
        /* setter风格
        JobDetail jobDetail=new JobDetailImpl("jobDetail1","group1",HelloQuartz.class);
        jobDetail.getJobDataMap().put("name", "quartz");

        SimpleTriggerImpl trigger=new SimpleTriggerImpl("trigger1","group1");
        trigger.setStartTime(new Date());
        trigger.setRepeatInterval(1);
        trigger.setRepeatCount(-1);*/
