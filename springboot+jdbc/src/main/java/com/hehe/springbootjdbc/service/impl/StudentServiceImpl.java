package com.hehe.springbootjdbc.service.impl;

import com.hehe.springbootjdbc.mapper.StudentMapper;
import com.hehe.springbootjdbc.mapper.StudentMapper3;
import com.hehe.springbootjdbc.pojo.Student;
import com.hehe.springbootjdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-07-07 23:29
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper3 studentMapper;


    @Override
    public List<Student> findNameandPage(Map<String, Object> param) {
        return studentMapper.findNameandPage(param);
    }
}
