package com.example.springswager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.springswager.mapper")  //扫描maper层
public class SpringswagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringswagerApplication.class, args);
    }

}
