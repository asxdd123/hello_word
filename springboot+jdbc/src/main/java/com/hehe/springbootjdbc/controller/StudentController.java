package com.hehe.springbootjdbc.controller;


import com.hehe.springbootjdbc.pojo.Student;
import com.hehe.springbootjdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-07-07 23:26
 */

@Controller
@RequestMapping("/hello")
public class StudentController {

    @Autowired
    private StudentService service;

    @RequestMapping("/hello")
    public String aa() {
        return "hello";
    }

    /**
     * 模糊分页查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public List<Student> selectPage(@RequestBody Map<String,Object> param) {
        return service.findNameandPage(param);
    }
}
