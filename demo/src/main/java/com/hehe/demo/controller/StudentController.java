package com.hehe.demo.controller;

import com.hehe.demo.bean.Student ;
import com.hehe.demo.service.StudentService ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class StudentController {

    @Autowired
    private StudentService service;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    /**
     * 测试
     *
     */
    @RequestMapping("/hello")
    public String hello(){
        logger.info("Springboot启动,开始查询");
        return "hello";
    }

    /**
     * 查全部
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/aa/{page}/{size}")
    public List<Map<String,Object>> select(@PathVariable("page") int page,@PathVariable("size") int size){
        logger.info("查询开始");
        List<Map<String,Object>> students = service.select(page, size);
        return students;
    }

    /**
     * 增
     * @return
     * @throws ParseException
     */
    @RequestMapping("/bb")
    public int insert() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2021-02-03");
        Student student = new Student();
        student.setSid("10");
        student.setSname("张三");
        student.setSage(date);
        student.setSsex("男");

        int i = service.insert(student);
        return i;
    }

    /**
     * 改
     * @return
     * @throws ParseException
     */
    @RequestMapping("/cc")
    public int update() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-04");
        Student student = new Student();
        student.setSid("09");
        student.setSname("张三");
        student.setSage(date);
        student.setSsex("男");

        int a = service.update(student);
        return a;
    }

    /**
     * 删
     * @return
     */
    @RequestMapping("/dd")
    public int delete(){
        int de = service.delete("10");
        return de;
    }

    /**
     *  根据姓名和生日查
     * @return
     * http://localhost:8888/hello/ee?name=张三&age=2021-02-03
     */
    @RequestMapping("/ee1")
    public Student selectName(@RequestParam("name") String sname,@RequestParam("age") String sage){
        Student stu = service.selectNameAndAge(sname,sage);
        return stu;
    }
    /**
     *  根据姓名和生日查
     * @param sname
     * @param sage
     * @return
     * http://localhost:8888/hello/ee2/张三/2021-02-03
     */
    @RequestMapping("/ee2/{name}/{age}")
    public Student selectName2(@PathVariable("name") String sname,@PathVariable("age") String sage){
        Student stu = service.selectNameAndAge(sname,sage);
        return stu;
    }


    /**
     * 根据多个id查
     * http://localhost:8888/hello/ff?ids=01&ids=02&ids=03
     */
    @RequestMapping("/ff")
    public List<Student> selectList(@RequestParam("ids") List<String> ids){
        List<Student> students = service.selectList(ids);
        return students;
    }

}
