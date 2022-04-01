package com.itheima.springbppt.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itheima.springbppt.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    List<Student> selectPage(@Param("queryString") String queryString);

    void add(Student student);

    Student selectid(String sid);

    void handleEdit(Student student);

    void deleteid(String sid);

    int count(String sname);
}
