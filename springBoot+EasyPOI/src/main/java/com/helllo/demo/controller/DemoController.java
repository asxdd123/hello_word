package com.helllo.demo.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.helllo.demo.pojo.Student;
import com.helllo.demo.service.StudentService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class DemoController {

    @Autowired
    private StudentService service;


    @RequestMapping("/aa")
    public String TestEssyPOI() throws IOException {
        List<Student> list = service.list();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("测试数据", ""), Student.class, list);
        FileOutputStream stream = new FileOutputStream("D://emp.xls");
        workbook.write(stream);
        workbook.close();
        stream.close();

        return null;
    }
}
