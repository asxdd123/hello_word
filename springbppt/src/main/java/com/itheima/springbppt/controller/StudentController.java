package com.itheima.springbppt.controller;

import com.itheima.springbppt.bean.Student;
import com.itheima.springbppt.fastdfs.FastDFSClientUtil;
import com.itheima.springbppt.mapper.StudentMapper;
import com.itheima.springbppt.service.StudentService;
import com.itheima.springbppt.utils.Excel.POIUtils;
import com.itheima.springbppt.utils.QueryPageBean;
import com.itheima.springbppt.utils.Result;
import com.itheima.springbppt.utils.ResultCode;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/hello")
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private FastDFSClientUtil dfsClientUtil;

    @Value("${web-server-url}")
    private String fileServerUrl;   //fastdfs图片url前半部分

    private final static Logger logger = LoggerFactory.getLogger(StudentController.class);

    /**
     * 跳转页面
     *
     * @return
     */
    @RequestMapping("/hello")
    public String toLogin() {
        return "hello";
    }

    /**
     * 跳转页面
     *
     * @return
     */
    @RequestMapping("/word")
    public String Word() {
        return "word";
    }


    /**
     * 查全部
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Result selectPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            Map<String, Object> map = service.selectPage(queryPageBean);
            return new Result(true, ResultCode.QUERY__SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, ResultCode.QUERY__FAIL);
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
        try {
            Student student = service.selectid(sid);
            return new Result(true, ResultCode.QUERY__SUCCESS, student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, ResultCode.QUERY__FAIL);
    }

    /**
     * 新增
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Student student) {
        try {
            String url = student.getUrl();
            System.out.println(url);

            service.add(student);
            return new Result(true, ResultCode.ADD__SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, ResultCode.ADD_FAIL);
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
        try {
            String url = student.getUrl();
            System.out.println(url);

            service.handleEdit(student);
            return new Result(true, ResultCode.EDIT__SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, ResultCode.EDIT__FAIL);
    }

    /**
     * 删除
     *
     * @param sid
     * @return
     */
    @RequestMapping(value = "/deleteid", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteid(@RequestParam("sid") String sid) {
        try {
            service.deleteid(sid);
            return new Result(true, ResultCode.DELETE__SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, ResultCode.DELETE__FAIL);
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
        List<Student> students = studentMapper.selectPage(null);
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
        List<Student> students = studentMapper.selectPage(null);
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

    /**
     * 下载Excel模板
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    @ResponseBody
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClassPathResource resource = new ClassPathResource("template\\人员管理.xlsx");
        //获取路径
        File file = resource.getFile();
        //获取名字
        String filename = resource.getFilename();
        FileInputStream inputStream = new FileInputStream(file);

        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        int b = 0;
        byte[] bytes = new byte[100000];
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

    /**
     * 导入Excel
     *
     * @param excelFile
     * @return
     */
    @RequestMapping(value = "/channel", method = RequestMethod.POST)
    @ResponseBody
    public Result channel(@RequestParam(value = "excelFile") MultipartFile excelFile) {
        System.out.println(excelFile);
        try {
            String filename = excelFile.getOriginalFilename();
            if (filename.isEmpty()) {
                return new Result(false, ResultCode.message);
            }
            List<String[]> strings = POIUtils.readExcel(excelFile);
            List<Student> students = new ArrayList<>();
            for (String[] string : strings) {
                Student stu = new Student();
                stu.setSid(string[0]);
                stu.setSname(string[1]);
                stu.setSage(string[2]);
                stu.setSsex(string[3]);

                students.add(stu);
            }
            if (students != null && students.size() > 0) {
                for (Student student : students) {
                    service.add(student);
                }
            }
            return new Result(true, ResultCode.DERIVE__SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, ResultCode.DERIVE__FAIL);
    }


    /**
     * 使用fastdfs上传图片
     *
     * @param picture
     * @return
     */
    @RequestMapping("/uploadPicture")
    @ResponseBody
    public Result uploadPicture(@RequestParam("picture") MultipartFile picture) {
        //判断文件是否存在
        try {
            if (picture == null) {
                return new Result(false, ResultCode.message);
            }
            //获取文件的完整名称
            String uploadFile = dfsClientUtil.uploadFile(picture);
            String url = fileServerUrl + uploadFile;
            String filename = picture.getOriginalFilename();
            logger.info(url);
/*
            //拿到url后可以保存到数据库进行操作
            //模拟添加学生数据
            Student stu = new Student();
            stu.setSid("39");
            stu.setSname("在执行执行");
            stu.setSage("2022-01-06");
            stu.setSsex("女");
            stu.setUrl(url);
            service.add(stu);*/
            return new Result(true, ResultCode.DERIVE__SUCCESS, url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, ResultCode.DERIVE__FAIL);
    }

    /**
     * fastdfs删除文件
     *
     * @param pdfFile
     * @return
     */
    @RequestMapping("/delPDF")
    @ResponseBody
    public Result delPDF(@RequestParam("pdfFile") String pdfFile) {
        if (pdfFile != null) {
            String[] split = pdfFile.split(fileServerUrl);
            dfsClientUtil.delFile(split[1]);
            return new Result(true, ResultCode.DELETE__SUCCESS);
        }
        return new Result(false, ResultCode.DERIVE__FAIL);
    }

    /**
     * fastdfs下载文件
     * @param url
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/dowan")
    @ResponseBody
    public Result dowan(@RequestParam("url") String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            //1.读取配置文件
            ClientGlobal.init("fastdfs-client.conf");
            //2.获得tracker和storage对象
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient client = new StorageClient(trackerServer, storageServer);
            //3.分割url
            String[] split = url.split(fileServerUrl);
            String[] strings = split[1].split("\\/", 2);
            //4.下载文件
            byte[] bytes = client.download_file(strings[0], strings[1]);
            //5.获取IO流进行输出
            ServletOutputStream stream = response.getOutputStream();
            if(bytes != null){
                stream.write(bytes);
            }
            //6.刷新关流
            stream.flush();
            stream.close();
            return new Result(true, ResultCode.QUERY__SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return new Result(true, ResultCode.QUERY__FAIL);
    }


    /**
     * fastdfs下载文件
     * @param url
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/dowanA")
    @ResponseBody
    public Result dowanA(@RequestParam("url") String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            String[] split = url.split(fileServerUrl);
            String[] strings = split[1].split("\\/", 2);
            byte[] bytes = dfsClientUtil.download(strings[0], strings[1]);

            ServletOutputStream stream = response.getOutputStream();
            if(bytes != null){
                stream.write(bytes);
            }
            stream.flush();
            stream.close();
            return new Result(true, ResultCode.QUERY__SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(true, ResultCode.QUERY__FAIL);
    }
}

