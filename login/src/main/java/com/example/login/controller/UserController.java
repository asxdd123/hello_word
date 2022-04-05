package com.example.login.controller;


import com.example.login.bean.User;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/aa")
public class UserController {
    @Autowired
    private UserService userService;

    //-----------------------------登录注册功能
    //登录：
    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/result")
    public String result(HttpServletRequest req, HttpServletResponse resp){

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(username);
        user.setPassword(password);

        boolean flag = userService.login(user);

        if(flag==true){
            return "success";
        }else {
            return "middle";
        }
    }
    @GetMapping("/middle")
    public String middle(){
        return "login";
    }


    //注册：
    @RequestMapping("/zhuce")
    public String zhuCe(){
        return "zhuce";
    }

    @GetMapping("/end")
    public String end(HttpServletRequest req, HttpServletResponse resp){

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(username);
        user.setPassword(password);
        boolean flag = userService.zhuCe(user);
        if(flag==true){
            return "end1";
        }else{
            return "end2";
        }
    }


}
