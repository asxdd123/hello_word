package com.momo.springaop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/helloAop/{id}")
    public Object hello(@PathVariable("id") Integer id){
        return "hello aop" + id;
    }
    @RequestMapping("/helloError")
    public Object helloError(){
        return 1/0;
    }
}

