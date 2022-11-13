package com.hehe.springbooredis.controller;

import com.hehe.springbooredis.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-11-13 22:28
 */
@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void Test() {
//        // 列表左边添加单个元素
//        redisTemplate.opsForList().leftPush("lk1", "v100");
//        // 列表左边添加多个元素
//        redisTemplate.opsForList().leftPushAll("lk1", "v100", "v200");
//        List list = redisTemplate.opsForList().range("lk1", 0, -1);
//        Object lk1 = redisTemplate.opsForList().rightPop("lk1");
//        Object lk2 = redisTemplate.opsForList().leftPop("lk1");
//        System.out.println("lk1==" + lk1);
//        System.out.println("lk2==" + lk2);
//        System.out.println("list===" + list);

        List<Student> students = Arrays.asList(
                new Student("张三", 18),
                new Student("李四", 20),
                new Student("王五", 22),
                new Student("赵六", 25)
        );

        redisTemplate.opsForList().rightPushAll("students", students);
        Long size = redisTemplate.opsForList().size("students");
        System.out.println("size==" + size);
        List list1 = redisTemplate.opsForList().range("students", 0, -1);
        System.out.println("rightPop==" + list1);
        Object students1 = redisTemplate.opsForList().rightPop("students");
        System.out.println("students1==" + students1);

    }
}
