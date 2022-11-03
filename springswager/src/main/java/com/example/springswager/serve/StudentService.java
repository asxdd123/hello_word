package com.example.springswager.serve;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springswager.entrny.Student;


import java.util.List;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-10-30 22:31
 */


public interface StudentService extends IService<Student> {
    List<Student> selectList();

    void insertStudentAAA(Student student);
}
