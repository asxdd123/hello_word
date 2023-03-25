package com.hehe.demo3;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.hehe.bean.Student;
//import com.hehe.service.StudentService;
import com.hehe.demo3.bean.Student;
import com.hehe.demo3.service.StudentService;
import com.hehe.demo3.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestB {

    @Autowired
    private StudentService service;

    /*根据id查*/
    @Test
    public void methods1() {
        /**
         * SELECT Sid,Sname,Sage,Ssex FROM student WHERE Sid=?
         */
        Student student = service.getById(15);
        System.out.println(student);
    }

    /*根据条件查询一条数据返回对象类型*/
    @Test
    public void methods19() {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("Sname","张三");
        /**
         * SELECT Sid,Sname,Sage,Ssex FROM student WHERE (Sname = ?)
         */
        Student student = service.getOne(wrapper);
        System.out.println(student);
    }

    /*根据条件查询数据返回map集合*/
    @Test
    public void methods20() {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("Sid","22")
                .eq("Sname","萨达姆");
        /**
         * SELECT Sid,Sname,Sage,Ssex FROM student WHERE (Sid = ? AND Sname = ?)
         */
        Map<String, Object> map = service.getMap(wrapper);
        System.out.println(map);


    }

    /*查全部(list形式)*/
    @Test
    public void methods2() {
        /**
         * SELECT Sid,Sname,Sage,Ssex FROM student
         */
        List<Student> studentList = service.list();
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    /*查总数*/
    @Test
    public void methods3() {
        /**
         * SELECT COUNT( 1 ) FROM student
         */
        int count = service.count();
        System.out.println(count);
    }

    /*查全部(map形式)*/
    @Test
    public void methods4() {
        /**
         * SELECT Sid,Sname,Sage,Ssex FROM student
         */
        List<Map<String, Object>> maps = service.listMaps();
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    /*根据条件查*/
    @Test
    public void methods5() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Sname", "我去");
        /**
         * SELECT Sid,Sname,Sage,Ssex FROM student WHERE Sname = ?
         */
        List<Student> students = service.listByMap(map);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /*分页模糊查询*/
    @Test
    public void methods6() {
        int current = 1;
        int size = 4;
        IPage page = new Page(current, size);
        /**
         * SELECT Sid,Sname,Sage,Ssex FROM student WHERE (Sname LIKE ?) LIMIT ?
         */
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.likeRight("Sname", "张");
        service.page(page, wrapper);

        List records = page.getRecords();
        for (Object record : records) {
            System.out.println("record=" + record);
        }
        long total = page.getTotal();
        System.out.println("total=" + total);
    }

    /*单个添加*/
    @Test
    public void methods7() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String age = "2022-01-05";
        Date date = format.parse(age);

        Student stu = new Student();
        stu.setSid("19");
        stu.setSname("三十多岁但是");
        stu.setSage(DateUtils.parseDate(age));
        stu.setSsex("男");

        boolean save = service.save(stu);
        System.out.println(save);
    }

    /*批量添加*/
    @Test
    public void methods8() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String age = "2022-01-05";
        Date date = format.parse(age);

        Student stu = new Student();
        stu.setSid("20");
        stu.setSname("飒飒啊");
        stu.setSage(DateUtils.parseDate(age));
        stu.setSsex("男");

        Student stu1 = new Student();
        stu1.setSid("21");
        stu1.setSname("小组组长向");
        stu1.setSage(DateUtils.parseDate(age));
        stu1.setSsex("男");

        Student stu2 = new Student();
        stu2.setSid("22");
        stu2.setSname("呈现出现场");
        stu2.setSage(DateUtils.parseDate(age));
        stu2.setSsex("男");

        ArrayList<Student> list = new ArrayList<>();
        list.add(stu);
        list.add(stu1);
        list.add(stu2);
        /**
         * INSERT INTO student ( Sid, Sname, Sage, Ssex ) VALUES ( ?, ?, ?, ? )
         */
        System.out.println("list="+list);
        boolean b = service.saveBatch(list);
        System.out.println(b);
    }

    /*该数据存在就更新,不存在就插入*/
    @Test
    public void methods9() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String age = "2022-01-05";
        Date date = format.parse(age);

        Student stu = new Student();
        stu.setSid("26");
        stu.setSname("欣赏欣赏");
        stu.setSage(DateUtils.parseDate(age));
        stu.setSsex("男");
        /**
         * 1.先查  SELECT Sid,Sname,Sage,Ssex FROM student WHERE Sid=?
         * 2.查到了进行修改  UPDATE student SET Sname=?, Sage=?, Ssex=? WHERE Sid=?
         */
        /**
         * 1.先查  SELECT Sid,Sname,Sage,Ssex FROM student WHERE Sid=?
         * 2. 没查到进行插入  INSERT INTO student ( Sid, Sname, Sage, Ssex ) VALUES ( ?, ?, ?, ? )
         */
        boolean b = service.saveOrUpdate(stu);
        System.out.println(b);
    }

    /*批量修改数据,如果数据存在就修改,不存在就插入*/
    @Test
    public void methods10() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String age = "2022-01-05";
        Date date = format.parse(age);

        Student stu = new Student();   //不存在
        stu.setSid("27");
        stu.setSname("向下执行在");
        stu.setSage(DateUtils.parseDate(age));
        stu.setSsex("男");

        Student stu2 = new Student();   //存在
        stu2.setSid("26");
        stu2.setSname("呈现出");
        stu2.setSage(DateUtils.parseDate(age));
        stu2.setSsex("男");

        ArrayList<Student> list = new ArrayList<>();
        list.add(stu);
        list.add(stu2);
        /**
         * 1. 先查        SELECT Sid,Sname,Sage,Ssex FROM student WHERE Sid=?
         * 2. 不存在就插入    INSERT INTO student ( Sid, Sname, Sage, Ssex ) VALUES ( ?, ?, ?, ? )
         * 3. 接着查       SELECT Sid,Sname,Sage,Ssex FROM student WHERE Sid=?
         * 4. 存在就修改  UPDATE student SET Sname=?, Sage=?, Ssex=? WHERE Sid=?
         */
        boolean b = service.saveOrUpdateBatch(list);
        System.out.println(b);
    }

    /*基于条件构造器修改,如果没有该数据则进行插入*/
    @Test
    public void methods11() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String age = "2022-01-05";
        Date date = format.parse(age);
        //创建修改条件构造器
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        //查询条件
        wrapper.eq("Sname","张重中之重");

        Student stu = new Student();
        stu.setSid("28");
        stu.setSname("张重中之重");
        stu.setSage(DateUtils.parseDate(age));
        stu.setSsex("男");
        /**
         * 1. 会先执行条件构造器                         UPDATE student SET Sname=?, Sage=?, Ssex=? WHERE (Sname = ?)
         * 2. 如果条件构造器获取不到, 会先查该数据是否存在   SELECT Sid,Sname,Sage,Ssex FROM student WHERE Sid=?
         * 3. 如果不存在就执行插入                        INSERT INTO student ( Sid, Sname, Sage, Ssex ) VALUES ( ?, ?, ?, ? )
         */
        boolean b = service.saveOrUpdate(stu,wrapper);
        System.out.println(b);
    }

    /*根据条件进行删除*/
    @Test
    public void methods12() {
        //创建条件构造器
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("Sid","27");
        /**
         * DELETE FROM student WHERE (Sid = ?)
         */
        boolean b = service.remove(wrapper);
        System.out.println(b);
    }

    /*根据id进行删除*/
    @Test
    public void methods13() {
        /**
         * DELETE FROM student WHERE Sid=?
         */
        boolean b = service.removeById("28");
        System.out.println(b);

    }

    /*基于map集合生成条件进行删除*/
    @Test
    public void methods14() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Sid","24");
        map.put("Sname","二位翁");
        /**
         * DELETE FROM student WHERE Sname = ? AND Sid = ?
         */
        boolean b = service.removeByMap(map);
        System.out.println(b);

    }

    /*根据id批量删除*/
    @Test
    public void methods15() {
        ArrayList<String> list = new ArrayList<>();
        list.add("23");
        list.add("24");

        boolean b = service.removeByIds(list);
        System.out.println(b);
    }

    /*根据条件构造器进行修改*/
    @Test
    public void methods16() {
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        wrapper.eq("Sid","22")
                .set("Sname","大傻子")
                .set("Ssex","女");
        /**
         * UPDATE student SET Sname=?,Ssex=? WHERE (Sid = ?)
         */
        boolean b = service.update(wrapper);
        System.out.println(b);
    }

    /*根据条件构造器进行修改*/
    @Test
    public void methods17() {
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        wrapper.eq("Sid","22");

        Student stu = new Student();
        stu.setSname("阿萨姆");
        /**
         * UPDATE student SET Sname=? WHERE (Sid = ?)
         */
        boolean b = service.update(stu, wrapper);
        System.out.println(b);
    }

    /*根据id进行修改*/
    @Test
    public void methods18() {
        Student stu = new Student();
        stu.setSid("22");
        stu.setSname("萨达姆");
        stu.setSsex("男");
        /**
         * UPDATE student SET Sname=?, Ssex=? WHERE Sid=?
         */
        boolean b = service.updateById(stu);
        System.out.println(b);
    }

    @Test
    public void methods22222() {
        String age = "2022-01-05";
        //创建修改条件构造器
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        //查询条件
        wrapper.eq("Sname","111");

        Student stu = new Student();
        stu.setSid("44");
        stu.setSname("111");
        stu.setSage(DateUtils.parseDate(age));
        stu.setSsex("男");
        /**
         * 1. 会先执行条件构造器                         UPDATE student SET Sname=?, Sage=?, Ssex=? WHERE (Sname = ?)
         * 2. 如果条件构造器获取不到, 会先查该数据是否存在   SELECT Sid,Sname,Sage,Ssex FROM student WHERE Sid=?
         * 3. 如果不存在就执行插入                        INSERT INTO student ( Sid, Sname, Sage, Ssex ) VALUES ( ?, ?, ?, ? )
         */
        boolean b = service.saveOrUpdate(stu,wrapper);
        System.out.println(b);
    }

}
