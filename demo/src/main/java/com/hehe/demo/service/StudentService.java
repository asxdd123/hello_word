package com.hehe.demo.service;

import com.hehe.demo.bean.Student ;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StudentService {

    public List<Map<String,Object>> select(int page,int size);

    public int insert(Student student);

    public int update(Student student);

    public int delete(String sid);

    public Student selectNameAndAge(@Param("name") String sname, @Param("age") String sage);

    List<Student> selectList(@Param("list") List<String> ids);

    int count();
}
