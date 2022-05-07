package com.helllo.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helllo.demo.mapper.StudentMapper;
import com.helllo.demo.pojo.Student;
import com.helllo.demo.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
