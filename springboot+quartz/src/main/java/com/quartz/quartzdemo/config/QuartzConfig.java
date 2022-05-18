package com.quartz.quartzdemo.config;

import com.quartz.quartzdemo.service.DateTimeJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   //标注是一个配置类
public class QuartzConfig {

    /**
     * 创建一个Job 任务
     * @return
     */
    @Bean
    public JobDetail printTimeJobDetail(){
        return JobBuilder.newJob(DateTimeJob.class)         //设置需要执行的定时任务类
                .withIdentity("DateTimeJob")                //可以给该JobDetail起一个名

                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("msg", "Hello Quartz")    //关联键值对
                .storeDurably()     //即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }

    /**
     * 创建Trigger  触发器
     * @return
     */
    @Bean
    public Trigger printTimeJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(printTimeJobDetail())           //关联上述的JobDetail
                .withIdentity("quartzTaskService")      //给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }

}
