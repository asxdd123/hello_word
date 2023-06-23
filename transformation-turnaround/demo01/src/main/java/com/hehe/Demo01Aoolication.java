package com.hehe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2023-04-13 18:37
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.hehe.mapper")
public class Demo01Aoolication {
    public static void main(String[] args) {
        SpringApplication.run(Demo01Aoolication.class, args);
    }
}
