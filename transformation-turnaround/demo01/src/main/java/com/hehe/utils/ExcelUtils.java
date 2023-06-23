package com.hehe.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2023-04-17 22:58
 */
public class ExcelUtils {


    /**
     * 下载Excel模板
     * @param resource
     * @param response
     */
    public static void downloadExcel(ClassPathResource resource, HttpServletRequest request, HttpServletResponse response) {
        File file = resource.getFile(); //获取文件路径
        String fileName = resource.getName();  //获取文件名
        if(!StringUtils.isEmpty(fileName) && ObjectUtil.isNotEmpty(file)){
            try (
                    FileInputStream inputStream = new FileInputStream(file);
                    ServletOutputStream outputStream = response.getOutputStream()
            ) {
                response.setContentType("application/force-download");
//                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                int b = 0;
                byte[] bytes = new byte[1024];
                while (b != -1) {
                    b = inputStream.read(bytes);
                    if (b != -1) {
                        outputStream.write(bytes, 0, b);
                    }
                }
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置导出数据请求响应正文
     * @param fileName
     * @param request
     * @param response
     */
    public static void deriveExcel(String fileName, HttpServletRequest request,HttpServletResponse response){
        try {
            fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            response.setContentType("application/vnd.ms-excel");
            response.setContentType("application/x-download");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public static OutputStream getOutputStream(String fileName, HttpServletRequest request,HttpServletResponse response) throws IOException {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        return response.getOutputStream();
    }
}
