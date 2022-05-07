package com.helllo.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.helllo.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {

//    @Select("select * from student")
//    List<Student> list();
}
