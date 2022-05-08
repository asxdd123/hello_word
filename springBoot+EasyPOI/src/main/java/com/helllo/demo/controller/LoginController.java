package com.helllo.demo.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.helllo.demo.pojo.*;
import com.helllo.demo.service.DproductService;
import com.helllo.demo.service.LoginService;
import com.helllo.demo.service.RegionEntityService;
import com.helllo.demo.service.StudentService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private RegionEntityService entityService;
    @Autowired
    private DproductService dproductService;

    @RequestMapping("/hello/{id}")
    public void exportEmployee(@PathVariable("id") Integer id, HttpServletResponse response) {
        Workbook workbook = null;
        String title = null;
        List<?> list;
        switch (id){
            case 1:
                list = studentService.list();
                title = "学生表.xls";
                workbook = ExcelExportUtil.exportExcel(new ExportParams("学生表", "sheetName", ExcelType.HSSF), Student.class, list);
                break;
            case 2:
                list = loginService.list();
                title = "员工表.xls";
                workbook = ExcelExportUtil.exportExcel(new ExportParams("员工表", "sheetname", ExcelType.HSSF), Login.class, list);
                break;
            case 3:
                list = entityService.list();
                title = "区域表.xls";
                workbook = ExcelExportUtil.exportExcel(new ExportParams("区域一览表", "sheetname", ExcelType.HSSF), RegionEntity.class, list);
                break;
            case 4:
                list = dproductService.productSelect();
                title = "产品表.xls";
                workbook = ExcelExportUtil.exportExcel(new ExportParams("产品信息表", "sheetname", ExcelType.HSSF), Dproduct.class, list);
                break;
        }
        methods1(response, workbook, title);
    }


    private void methods1(HttpServletResponse response, Workbook workbook, String title) {
        //以流的形式导出
        ServletOutputStream outputStream = null;
        try {
            //流形式
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(title, "UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @RequestMapping("/bb")
    public void productSelect(){
        List<Dproduct> dproducts = dproductService.productSelect();
        for (Dproduct dproduct1 : dproducts) {
            System.out.println(dproduct1.getId() +" "+ dproduct1.getProductName());
            List<DImg> dImgs = dproduct1.getChildren();
            for (DImg dImg : dImgs) {
                System.out.println("\t" + dImg);
            }
        }
    }


}
