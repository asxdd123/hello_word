package com.hehe.springbootjdbc.service;

import com.hehe.springbootjdbc.pojo.Student;

import java.util.List;
import java.util.Map;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-07-07 23:27
 */
public interface StudentService {
    List<Student> findNameandPage(Map<String, Object> param);
}
