package com.itheima.springbppt.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.springbppt.bean.Student;
import com.itheima.springbppt.mapper.StudentMapper;
import com.itheima.springbppt.service.StudentService;
import com.itheima.springbppt.utils.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 新增
     *
     * @param student
     */
    @Override
    public void add(Student student) {
        try {
            Student stu = new Student();
            String sage = student.getSage();
            if(sage.contains("T")){
                int index = sage.indexOf("T");
                String s = sage.substring(0, index);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                Date date = dateFormat.parse(s);
                Calendar ins = Calendar.getInstance();
                ins.setTime(date);
                ins.add(Calendar.DATE, 1);
                Date time = ins.getTime();
                String format = dateFormat.format(time);
                stu.setSage(format);
            }else{
                stu.setSage(student.getSage());
            }
            stu.setSname(student.getSname());
            stu.setSsex(student.getSsex());

            int count = studentMapper.count(stu.getSname());
            if (count > 0) {
                studentMapper.handleEdit(stu);
            } else {
                studentMapper.add(stu);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id查
     *
     * @param sid
     * @return
     */
    @Override
    public Student selectid(String sid) {
        Student student = studentMapper.selectid(sid);
        return student;
    }

    /**
     * 编辑
     *
     * @param student
     */
    @Override
    public void handleEdit(Student student) {
        Student stu = new Student();
        stu.setSid(student.getSid());
        stu.setSname(student.getSname());
        stu.setSsex(student.getSsex());
        String sage = student.getSage();
        if (sage.contains("T")) {
            int index = sage.indexOf("T");
            String sub = sage.substring(0, index);
            stu.setSage(sub);
        } else {
            stu.setSage(student.getSage());
        }
        studentMapper.handleEdit(stu);
    }

    /**
     * 删除
     *
     * @param sid
     */
    @Override
    public void deleteid(String sid) {
        studentMapper.deleteid(sid);
    }

    @Override
    public void addList(List<Student> students) {

    }


    /**
     * 分页查全部
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public Map<String, Object> selectPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage, pageSize);
        List<Student> students = studentMapper.selectPage(queryString);
        PageInfo<Student> info = new PageInfo<>(students);
        List<Student> list = info.getList();
        long total = info.getTotal();

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("total", total);
        return map;
    }
}
