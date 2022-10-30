package com.example.springswager.serve.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springswager.entrny.Student;
import com.example.springswager.mapper.StudentMapper;
import com.example.springswager.serve.StudentService;
import com.example.springswager.util.ResponseVo;
import com.example.springswager.util.ResponseVoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-10-30 22:31
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private  StudentMapper studentMapper;


    @Override
    public List<Student> selectList() {
        int pageNum = 1;
        int pageSize = 10;
        Page<Student> studentPage = studentMapper.selectPage(new Page<Student>(pageNum, pageSize), null);
        return studentPage.getRecords();
    }

}
