package com.hehe.demo3.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hehe.demo3.bean.Student;
import com.hehe.demo3.mapper.StudentMapper;
import com.hehe.demo3.service.StudentService;
import com.hehe.demo3.utils.QueryPageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper mapper;

    /**
     * 删除
     * @param sid
     * @return
     */
    @Override
    public int deleteid(String sid) {
        int id = mapper.deleteById(sid);
        return id;
    }

    /**
     * 编辑
     * @param student
     * @return
     *
     * 修改的时候一定要加条件,不加条件会修改所有
     */
    @Override
    public int handleEdit(Student student) {
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        wrapper.eq("Sid",student.getSid());
        int update = mapper.update(student, wrapper);
        return update;
    }

    /**
     * 根据id查
     * @param sid
     * @return
     */
    @Override
    public Student selectid(String sid) {
        Student student = mapper.selectById(sid);
        return student;
    }

    /**
     * 新增
     * @param student
     * @return
     */
    @Override
    public int add(Student student) {
        String sage = student.getSage();
        if(sage.contains("T")){
            String t = sage.substring(0, sage.indexOf("T"));
            student.setSage(t);
        }else{
            student.setSage(sage);
        }

        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        //排序查出最后一个
        wrapper.orderByDesc(Student::getSid).last("limit 1");
        Student stu = mapper.selectOne(wrapper);
        String sid = stu.getSid();
        int anInt = Integer.parseInt(sid) + 1;
        String Sid = String.valueOf(anInt);
        student.setSid(Sid);

        int insert = mapper.insert(student);
        return insert;
    }

    /**
     * 模糊分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public Map<String, Object> findNameandPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        if(StringUtils.isNoneEmpty(queryString)){
            wrapper.like("Sname",queryString);
        }
        IPage page = new Page(currentPage, pageSize);
        IPage iPage = mapper.selectPage(page, wrapper);

        HashMap<String, Object> map = new HashMap<>();
        map.put("data",iPage.getRecords());
        map.put("total",iPage.getTotal());
        return map;
    }
}
