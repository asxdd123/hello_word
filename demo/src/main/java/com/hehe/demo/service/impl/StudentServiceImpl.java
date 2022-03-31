package com.hehe.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.hehe.demo.bean.Student ;
import com.hehe.demo.mapper.StudentMapper ;
import com.hehe.demo.service.StudentService ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Map<String, Object>> select(int page, int size) {
        PageHelper.startPage(page,size);
        List<Map<String, Object>> select = studentMapper.select();
        return select;
    }

    @Override
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public int delete(String sid) {
        return studentMapper.delete(sid);
    }

    @Override
    public Student selectNameAndAge(String sname, String sage) {
        return studentMapper.selectNameAndAge(sname,sage);
    }

    @Override
    public List<Student> selectList(List<String> ids) {
        return studentMapper.selectList(ids);
    }

    @Override
    public int count() {
        return 0;
    }


}
