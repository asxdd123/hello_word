package com.hehe.demo3.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hehe.demo3.bean.Student;
import com.hehe.demo3.service.StudentService;
import com.hehe.demo3.utils.DateUtils;
import com.hehe.demo3.utils.Excel.POIUtils;
import com.hehe.demo3.utils.QueryPageBean;
import com.hehe.demo3.utils.Result;
import com.hehe.demo3.utils.ResultCode;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/aa")
public class Demo3Controller {

    @Autowired
    private StudentService service;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginIndex() {
        return "login";
    }

    @GetMapping("/result")
    public String result(HttpServletRequest req, HttpServletResponse resp){

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Student student = new Student();
        student.setSname(username);

        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Student::getSname,username);
        Student one = service.getOne(wrapper);
        if(ObjectUtils.isNotEmpty(one)){
            return "hello";
        }else{
            return "zhuce";
        }
    }


    @GetMapping("/end")
    public String end(HttpServletRequest req, HttpServletResponse resp){

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Student user = new Student();
        user.setSid("45");
        user.setSname(username);
        service.save(user);
        req.getSession().setAttribute("sessionid",user);
        req.getSession().setMaxInactiveInterval(3*60);  //以秒为单位，即在没有活动3分钟后，session将失效
        return "login";
    }


    /**
     * 模糊分页查询
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Result selectPage(@RequestBody QueryPageBean queryPageBean) {
        if (queryPageBean != null) {
            try {
                Map<String, Object> map = service.findNameandPage(queryPageBean);
                return new Result(true, ResultCode.QUERY__SUCCESS, map);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, ResultCode.QUERY__FAIL);
            }
        } else {
            return new Result(false, ResultCode.message);
        }
    }


    /**
     * 新增
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Student student) {
        if (student != null) {
            int num = service.add(student);
            if (num == 1) {
                return new Result(true, ResultCode.ADD__SUCCESS);
            } else {
                return new Result(false, ResultCode.ADD_FAIL);
            }
        } else {
            return new Result(false, ResultCode.message);
        }

    }


    /**
     * 根据id查数据
     *
     * @param sid
     * @return
     */
    @RequestMapping(value = "/selectid", method = RequestMethod.POST)
    @ResponseBody
    public Result selectid(@RequestParam("sid") String sid) {
        if (sid != null) {
            try {
                Student student = service.selectid(sid);
                return new Result(true, ResultCode.QUERY__SUCCESS, student);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Result(true, ResultCode.QUERY__FAIL);
        } else {
            return new Result(false, ResultCode.message);
        }
    }


    /**
     * 编辑
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "/handleEdit", method = RequestMethod.POST)
    @ResponseBody
    public Result handleEdit(@RequestBody Student student) {
        if (student != null) {
            try {
                int num = service.handleEdit(student);
                if (num == 1) {
                    return new Result(true, ResultCode.EDIT__SUCCESS);
                } else {
                    return new Result(false, ResultCode.EDIT__FAIL);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Result(false, ResultCode.message);
    }


    /**
     * 根据id删除
     *
     * @param sid
     * @return
     */
    @RequestMapping(value = "/deleteid", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteid(@RequestParam String sid) {
        if (sid != null) {
            try {
                int num = service.deleteid(sid);
                if (num == 1) {
                    return new Result(true, ResultCode.DELETE__SUCCESS);
                } else {
                    return new Result(false, ResultCode.DELETE__FAIL);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Result(false, ResultCode.message);
    }


    /**
     * 下载模板
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    @ResponseBody
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClassPathResource resource = new ClassPathResource("template\\人员管理.xlsx");
        File file = resource.getFile();
        String filename = resource.getFilename();
        FileInputStream in = new FileInputStream(file);

        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));

        int b;
        byte[] bytes = new byte[1024];
        while ((b = in.read(bytes)) != -1) {
            out.write(bytes, 0, b);
        }
        in.close();
        out.flush();
        out.close();
    }


    /**
     * 导入
     *
     * @param excelFile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/channel", method = RequestMethod.POST)
    @ResponseBody
    public Result downloadExcel(@RequestParam("excelFile") MultipartFile excelFile) throws IOException {
        String filename = excelFile.getOriginalFilename();

        if (filename != null){
            List<String[]> strings = POIUtils.readExcel(excelFile);
            ArrayList<Student> list = new ArrayList<>();
            for (String[] string : strings) {
                Student stu = new Student();
                stu.setSid(string[0]);
                stu.setSname(string[1]);
                stu.setSage(DateUtils.parseDate(string[2]));
                stu.setSsex(string[3]);
                list.add(stu);
            }
            if(list != null && list.size() > 0){
                boolean result = service.saveBatch(list);
                if(result == true){
                    return new Result(true,ResultCode.DERIVE__SUCCESS);
                }else{
                    return new Result(false,ResultCode.DERIVE__FAIL);
                }
            }
        }
        return new Result(false, ResultCode.DERIVE__FAIL);
    }

    /**
     * 代码生成Excel文件导出
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/derivePage", method = RequestMethod.GET)
    @ResponseBody
    public void derivePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("人员管理");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("生日");
        row.createCell(3).setCellValue("性别");
//        List<Student> students = studentMapper.selectPage(null);
        List<Student> students = service.list();

        for (int i = 0; i < students.size(); i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(students.get(i).getSid());
            row1.createCell(1).setCellValue(students.get(i).getSname());
            row1.createCell(2).setCellValue(students.get(i).getSage());
            row1.createCell(3).setCellValue(students.get(i).getSsex());
        }
        ServletOutputStream outputStream = response.getOutputStream();
        response.reset();   //清除首部的空白行,去缓存
        String fileName = "人员管理.xlsx";
        fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");   //如果不设置,下载文件不显示中文名称
//        通知客户端以下载的方式接受数据,设置响应头，控制浏览器下载该文件
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
//        response.setContentType("application/x-xls");
        response.setContentType("application/vnd.ms-excel");   //告知客户端响应正文类型
        response.setContentType("application/x-download");

        request.setCharacterEncoding("UTF-8"); //设置对客户端请求进行重新编码的编码
        response.setCharacterEncoding("UTF-8");

        workbook.write(outputStream);
        if (workbook != null) {
            workbook.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }


    /**
     * 基于Excel模板导出数据
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/deriveExcel", method = RequestMethod.GET)
    @ResponseBody
    public void deriveExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ClassPathResource resource = new ClassPathResource("template\\人员管理.xlsx");
        File file = resource.getFile();
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
//        List<Student> students = studentMapper.selectPage(null);
        List<Student> students = service.list();

        for (int i = 0; i < students.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(students.get(i).getSid());
            row.createCell(1).setCellValue(students.get(i).getSname());
            row.createCell(2).setCellValue(students.get(i).getSage());
            row.createCell(3).setCellValue(students.get(i).getSsex());
        }
        ServletOutputStream outputStream = response.getOutputStream();
        response.reset();   //清除首部的空白行,去缓存
        String fileName = "人员.xlsx";
        fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");   //如果不设置,下载文件不显示中文名称
//        通知客户端以下载的方式接受数据,设置响应头，控制浏览器下载该文件
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
//        response.setContentType("application/x-xls");
        response.setContentType("application/vnd.ms-excel");   //告知客户端响应正文类型
        response.setContentType("application/x-download");

        request.setCharacterEncoding("UTF-8"); //设置对客户端请求进行重新编码的编码
        response.setCharacterEncoding("UTF-8");

        workbook.write(outputStream);
        if (workbook != null) {
            workbook.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }

}
