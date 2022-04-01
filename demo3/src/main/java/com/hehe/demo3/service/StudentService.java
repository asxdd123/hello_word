package com.hehe.demo3.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hehe.demo3.bean.Student;
import com.hehe.demo3.utils.QueryPageBean;

import java.util.ArrayList;
import java.util.Map;

public interface StudentService extends IService<Student> {

    Map<String, Object> findNameandPage(QueryPageBean queryPageBean);

    int add(Student student);

    Student selectid(String sid);

    int handleEdit(Student student);

    int deleteid(String sid);

}
