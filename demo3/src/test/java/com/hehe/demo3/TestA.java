package com.hehe.demo3;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hehe.demo3.bean.Student;
import com.hehe.demo3.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestA {
    @Autowired
    private StudentMapper mapper;

    /*根据单个id查*/
    @Test
    public void methods1() {
        /**
         * SELECT sid,Sname,Sage,Ssex FROM student WHERE sid=?
         */
        Student student = mapper.selectById(11);
        System.out.println(student);
    }

    /*根据多个id查*/
    @Test
    public void methods2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);
        list.add(13);
        /**
         * SELECT sid,Sname,Sage,Ssex FROM student WHERE sid IN ( ? , ? , ? )
         */
        List<Student> students = mapper.selectBatchIds(list);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /*根据条件查*/
    @Test
    public void methods3() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Sname", "孙风");
        /**
         * SELECT sid,Sname,Sage,Ssex FROM student WHERE Sname = ?
         */
        List<Student> students = mapper.selectByMap(map);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /*新增*/
    @Test
    public void methods4() throws ParseException {
        Student student = new Student();
        student.setSid("20");
        student.setSname("大萨达");
        String age = "2022-01-05";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(age);
        student.setSage(date);
        student.setSsex("男");
        /**
         * INSERT INTO student ( Sid, Sname, Sage, Ssex ) VALUES ( ?, ?, ?, ? )
         */
        int insert = mapper.insert(student);
        System.out.println(insert);

    }

    /*修改*/
    @Test
    public void methods5() {
        Student student = new Student();
        student.setSid("19");
        student.setSname("大傻逼");
        /**
         * UPDATE student SET Sname=? WHERE Sid=?
         */
        int i = mapper.updateById(student);
        System.out.println(i);
    }

    /*删除*/
    @Test
    public void methods6() {
        /**
         * DELETE FROM student WHERE Sid=?
         */
        int i = mapper.deleteById("19");
        System.out.println(i);
    }

    @Test
    public void methods7() {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        wrapper.eq("Sname","我去")
//                .or()
//                .lt("Ssex","男")
//                .in("name","我去","二位")
                //.orderBy(true,true,"age")
                .select("Sname","Ssex");
        /**
         * SELECT Sname,Ssex FROM student WHERE (Sname = ?)
         */
        List<Student> students = mapper.selectList(wrapper);
        System.out.println(students);
    }

    /*分页查*/
    @Test
    public void methods8() {
        int current = 1;
        int size = 4;
        IPage page = new Page(current, size);
    /**
     * SELECT Sid,Sname,Sage,Ssex FROM student LIMIT ?
     */
        IPage iPage = mapper.selectPage(page, null);

        long total = iPage.getTotal();
        List records = iPage.getRecords();
        for (Object record : records) {
            System.out.println("record="+record);
        }
        System.out.println("total="+total);
    }

    /*链式查询,普通*/
    @Test
    public void methods9() {
        //创建条件构造
        QueryChainWrapper<Student> chainWrapper = new QueryChainWrapper<>(mapper);
        /**
         * SELECT Sid,Sname,Sage,Ssex FROM student WHERE (Sid = ?)
         */
        Object one = chainWrapper.eq("Sid", "22").one();
        System.out.println(one);
    }

    @Test
    public void methods10() {
        int current = 1;
        int size = 10;
        IPage page = new Page(current, size);

        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.like("Sname","张三");

        /**
         * 1. SELECT COUNT(1) FROM student WHERE (Sname LIKE ?)
         * 2. SELECT Sid,Sname,Sage,Ssex FROM student WHERE (Sname LIKE ?) LIMIT ?
         */
        IPage iPage = mapper.selectPage(page, wrapper);

        long total = iPage.getTotal();
        List records = iPage.getRecords();
        for (Object record : records) {
            System.out.println("record="+record);
        }
        System.out.println("total="+total);
    }
}





