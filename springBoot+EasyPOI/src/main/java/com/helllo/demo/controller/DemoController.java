package com.helllo.demo.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.word.WordExportUtil;
import cn.hutool.core.date.DateUtil;
import com.deepoove.poi.XWPFTemplate;
import com.helllo.demo.pojo.Student;
import com.helllo.demo.service.StudentService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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


    /**
     * 测试easypoi根据模板导出word
     *
     * 注意： 模板中的{}为中文符号，否则编译不通过
     * @param request
     * @param response
     */
    @RequestMapping("/bb")
    public void TestEasyPOI(HttpServletRequest request, HttpServletResponse response) {
        try {
            ClassPathResource resource = new ClassPathResource("template\\变电检修中心安全督察周报.docx");
            System.out.println(resource.getPath());
            long length = resource.getFile().length();
            System.out.println(length);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("time", DateUtil.format(new Date(), "yyyy年MM月dd日"));
            XWPFDocument word = WordExportUtil.exportWord07(resource.getPath(), hashMap);
            response.setHeader("content-disposition", "attachment;filename=" + new String("变电检修中心安全督察周报.docx".getBytes(), "ISO8859-1"));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ServletOutputStream outputStream = response.getOutputStream();
            word.write(outputStream);
            outputStream.close();
            word.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试使用poi-tl导出word文档
     * @param request
     * @param response
     */
    @RequestMapping("/cc")
    public void TestPoiTl(HttpServletRequest request, HttpServletResponse response) {
        try {
            ClassPathResource resource = new ClassPathResource("template\\变电检修中心安全督察周报.docx");
            System.out.println(resource.getPath());
            long length = resource.getFile().length();
            System.out.println(length);

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("time", DateUtil.format(new Date(), "yyyy年MM月dd日"));

            response.setHeader("content-disposition", "attachment;filename=" + new String("变电检修中心安全督察周报.docx".getBytes(), "ISO8859-1"));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            //最终编译渲染并输出
            ServletOutputStream outputStream = response.getOutputStream();
            XWPFTemplate.compile(resource.getFile()).render(hashMap).writeAndClose(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
