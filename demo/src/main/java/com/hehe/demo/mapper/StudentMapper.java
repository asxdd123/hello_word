package com.hehe.demo.mapper;


import com.hehe.demo.bean.Student ;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StudentMapper {

    public List<Map<String,Object>> select();

    public int insert(Student student);

    public int update(Student student);

    public int delete(String sid);

    Student selectNameAndAge(@Param("name") String sname, @Param("age") String sage);

    List<Student> selectList(@Param("list") List<String> ids);
}
