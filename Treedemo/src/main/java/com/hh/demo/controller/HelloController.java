package com.hh.demo.controller;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/word")
public class HelloController {


    @RequestMapping("/word")
    public String hello() {
        return "word";
    }


    /**
     * ClassPathResource类，如果没有指定相对的类名，该类将从类的根路径开始寻找某个resource，如果指定了相对的类名，则根据指定类的相对路径来查找某个resource。
     * Resource rs = new ClassPathResource("DispatcherServlet.properties");
     * 或者
     * Resource rs = new ClassPathResource("DispatcherServlet.properties",DispatcherServlet.class);
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClassPathResource resource = new ClassPathResource("template\\模板.xlsx");
        //获取路径
        File file = resource.getFile();
        //获取名字
        String filename = resource.getFilename();
        FileInputStream inputStream = new FileInputStream(file);

        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        int b = 0;
        byte[] bytes = new byte[1024];
        while (b != -1) {
            b = inputStream.read(bytes);
            if (b != -1) {
                outputStream.write(bytes, 0, b);
            }
        }
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }



}
