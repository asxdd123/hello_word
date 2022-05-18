package com.example.excptionto.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TestController {

    @RequestMapping("/hello/{id}")
    public String hello(@PathVariable("id") Integer id) {
        int i = 1 / 0;
        return "hello" + id;
    }
}
