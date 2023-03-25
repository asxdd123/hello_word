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
import com.hehe.demo3.utils.DateUtils;
import com.hehe.demo3.utils.QueryPageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        Date sage = student.getSage();
        String date = DateUtils.formatDate(sage);
        if(date.contains("T")){
            String t = date.substring(0, date.indexOf("T"));
            Date parseDate = DateUtils.parseDate(t);
            student.setSage(parseDate);
        }else{
            student.setSage(sage);
        }

        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        //倒序查询获取第一个对象
        wrapper.orderByDesc(Student::getSid).last("limit 1");
        //获取该对象的id
        Student stu = mapper.selectOne(wrapper);
        String sid = stu.getSid();
        //给获取的id+1得到新的id塞给变量student
        int anInt = Integer.parseInt(sid) + 1;
        String Sid = String.valueOf(anInt);
        student.setSid(Sid);
        //添加   实现id自增效果(前提是 数据库没有设置id自增)
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
