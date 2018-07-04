package com.quartz.zml.simple2;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * say hello and weather 这里属性传递采用了两种方式
 * @author zml
 * @date 2018/7/4
 */
@DisallowConcurrentExecution
public class SayHelloAndWeatherJob implements Job {

    private String name;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date()) + "say hello to " + name
                + " and say weather is " + dataMap.get("weather"));

    }

    public void setName(String name) {
        this.name = name;
    }
}
