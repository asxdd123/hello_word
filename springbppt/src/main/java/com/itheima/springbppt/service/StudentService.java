package com.itheima.springbppt.service;



import com.itheima.springbppt.bean.Student;
import com.itheima.springbppt.utils.QueryPageBean;


import java.util.List;
import java.util.Map;

public interface StudentService {

    Map<String,Object> selectPage(QueryPageBean queryPageBean);

    void add(Student student);

    Student selectid(String sid);

    void handleEdit(Student student);

    void deleteid(String sid);

    List<Student> selectMap(Map<String, Object> param);
}
