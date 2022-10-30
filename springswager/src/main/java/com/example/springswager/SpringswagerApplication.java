package com.example.springswager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.example.springswager.mapper")  //扫描maper层
public class SpringswagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringswagerApplication.class, args);
    }

}
