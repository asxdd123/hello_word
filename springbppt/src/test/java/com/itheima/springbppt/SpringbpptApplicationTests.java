package com.itheima.springbppt;


import com.itheima.springbppt.bean.Student;
import com.itheima.springbppt.mapper.StudentMapper;
import com.itheima.springbppt.service.StudentService;
import com.itheima.springbppt.utils.Excel.POIUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@SpringBootTest
class SpringbpptApplicationTests {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() throws Exception {
        List<Student> students = studentMapper.selectPage(null);
        XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream stream = new FileOutputStream("C:\\Users\\就不告诉你\\Desktop\\aa\\新建 Microsoft Excel 工作表.xlsx");
        XSSFSheet sheet = workbook.createSheet("人员管理");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("生日");
        row.createCell(3).setCellValue("性别");

        for (int i = 0; i < students.size(); i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(students.get(i).getSid());
            row1.createCell(1).setCellValue(students.get(i).getSname());
            row1.createCell(2).setCellValue(students.get(i).getSage());
            row1.createCell(3).setCellValue(students.get(i).getSsex());
        }
        workbook.write(stream);
        stream.flush();
        if (workbook != null) {
            workbook.close();
        }
        if (stream != null) {
            stream.close();
        }
    }

    @Test
    void Testa() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("1", "dsds");
        map.put("2", "颠三倒四多");

        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Object value = entry.getValue();
            System.out.println(value);
        }
    }

    @Test
    void Testac00() throws InvalidFormatException, IOException {
        File file = new File("C:\\Users\\就不告诉你\\Desktop\\人员管理.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();

        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                ArrayList<String> list = new ArrayList<>();
                for (Cell cell : row) {
                    if (cell != null) {
                        String cellValue = POIUtils.getCellValue(cell);
                        if (cellValue != null && cellValue != "") {
                            list.add(cellValue);
                        }
                    }
                }
                if (list.size() > 0) {
                    Student stu = new Student();
                    stu.setSid(list.get(0));
                    stu.setSname(list.get(1));
                    stu.setSage(list.get(2));
                    stu.setSsex(list.get(3));

                    students.add(stu);

                }
            }
        }
        //读取到了Excel中的信息
        System.out.println(students);
        for (Student student : students) {
            studentMapper.add(student);
        }
        workbook.close();
    }

    @Test
    void Testac01() throws ParseException {
        String s = "2021-02-05";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = dateFormat.parse(s);
        Calendar ins = Calendar.getInstance();
        ins.setTime(date);
        ins.add(Calendar.DATE, 1);
        Date time = ins.getTime();
        String format = dateFormat.format(time);
        System.out.println(format);
    }

    @Test
    void Testac02() throws ParseException {
        String s = "2021-02-05";
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = dateFormat.parse(s);
        instance.setTime(date);
        instance.add(Calendar.YEAR, 1);
        Date time = instance.getTime();
        String format = dateFormat.format(time);
        System.out.println(format);
    }

    @Test
    void Testac03() throws Exception {
        ClassPathResource resource = new ClassPathResource("template\\人员管理.xlsx");
        File file = resource.getFile();
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Student> students = studentMapper.selectPage(null);
        for (int i = 0; i < students.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(students.get(i).getSid());
            row.createCell(1).setCellValue(students.get(i).getSname());
            row.createCell(2).setCellValue(students.get(i).getSage());
            row.createCell(3).setCellValue(students.get(i).getSsex());
        }

        FileOutputStream stream = new FileOutputStream("C:\\Users\\就不告诉你\\Desktop\\人员.xlsx");
        workbook.write(stream);

        if(workbook != null){
            workbook.close();
        }
        if(stream != null){
            stream.flush();
            stream.close();
        }
    }
}
