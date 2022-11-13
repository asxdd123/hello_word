package com.hehe.springbooredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbooredisApplicationTests {

    //本地的redis不用配置,默认就是127.0.0.1:6379
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("code",18);
    }

    @Test
    void contextLoads2() {
        Object age = redisTemplate.opsForValue().get("code");
        System.out.println(age);
    }

}
