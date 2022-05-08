package com.helllo.demo;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

//    @Test
//    void contextLoads() throws IOException {
//        List<CourseEntity> courseEntityList = new ArrayList<>();
//
//        CourseEntity courseEntity = new CourseEntity();
//        courseEntity.setId("1");
//        courseEntity.setName("测试课程");
//
//        Teacher teacherEntity = new Teacher();
//        teacherEntity.setName("张老师");
//        teacherEntity.setSex(1);
//
//        courseEntity.setMathTeacher(teacherEntity);
//
//        List<Stu> studentEntities = new ArrayList<>();
//
//        for (int i = 1; i <= 2; i++) {
//            Stu studentEntity = new Stu();
//            studentEntity.setName("学生" + i);
//            studentEntity.setSex(i);
//            studentEntity.setBirthday(new Date());
//            studentEntities.add(studentEntity);
//        }
//        courseEntity.setStudents(studentEntities);
//
//        courseEntityList.add(courseEntity);
//        Date start = new Date();
//        Workbook workbook = ExcelExportUtil.exportExcel( new ExportParams("导出测试", null, "测试"),
//                CourseEntity.class, courseEntityList);
//        System.out.println(new Date().getTime() - start.getTime());
//        File savefile = new File("D:/excel/");
//        if (!savefile.exists()) {
//            savefile.mkdirs();
//        }
//        FileOutputStream fos = new FileOutputStream("D:/excel/教师课程学生导出测试.xls");
//        workbook.write(fos);
//        fos.close();
//    }

}
