package com.example.springswager.task;

import com.example.springswager.entrny.Student;
import com.example.springswager.serve.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-10-31 22:01
 */

@Component
public class ScheduleTask {

    @Autowired
    private StudentService studentService;

    @Scheduled(cron = "0 */1 * * * ?")  //每隔一分钟执行一次
    public void chedulet() {
        System.out.println("定时任务开启" + new Date());
        List<Student> list = studentService.selectList();
        System.out.println(list);
        System.out.println();
        System.out.println("=======================");
        System.out.println("定时任务结束");
    }
}
