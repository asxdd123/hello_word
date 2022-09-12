package com.heim.longindenglu.controller;

import com.heim.longindenglu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-07-13 23:36
 */

@Controller
@RequestMapping("/aa")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳往登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping("/zhuce")
    public String zhuce() {
        return "zhuce";
    }

    @RequestMapping("/end")
    public String end(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int count = userService.selectCount(username);
        //该用户名已存在
        if (count == 1) {
            return "redirect:zhuce";  //重定向
//            return "forward:zhuce";  //转发
//            return "end2";
        } else {
            int num = userService.insetUser(username, password);
           if(num == 1){
               request.setAttribute("username",username);
               request.setAttribute("password",password);
               return "end1";
           }else{
               return "end2";
           }
        }
    }



    /**
     * 登录校验
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/result")
    public String result(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean flag = userService.result(username, password);
        if (flag == true) {
            return "success";
        } else {
            return "middle";
        }
    }
}
