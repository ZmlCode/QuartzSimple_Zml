package com.quartz.zml.simple1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * say hello
 * @author zml
 * @date 2018/7/4
 */
public class SayHelloJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //Job.execute()方法是不允许抛出除JobExecutionException之外的所有异常的（包括RuntimeException)。
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("say hello ：" + format.format(new Date()));

    }
}
