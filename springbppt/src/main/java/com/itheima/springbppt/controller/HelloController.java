package com.itheima.springbppt.controller;

import com.itheima.springbppt.bean.Student;
import com.itheima.springbppt.fastdfs.FastDFSClientUtil;
import com.itheima.springbppt.service.StudentService;
import com.itheima.springbppt.utils.Result;
import com.itheima.springbppt.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/word")
public class HelloController {

    @Value("${web-server-url}") //fastdfs图片url前半部分
    private String fileUrl;
    @Autowired
    private FastDFSClientUtil dfsClientUtil;
    @Autowired
    private StudentService service;
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
     * 上传文件到fastdgs
     * @param pdfFile
     * @return
     */
    @RequestMapping("/downPDF")
    @ResponseBody
    public Result uploadPicture(@RequestBody MultipartFile pdfFile) {
        if (pdfFile == null) {
            return new Result(false, ResultCode.message);
        }
        try {
            String file = dfsClientUtil.uploadFile(pdfFile);
            System.out.println(file);
            String url = fileUrl + file;
            System.out.println(url); //http://192.168.23.147/group1/M00/00/00/wKgXk2JBJXWAXerqAAadrz7KKyw297.jpg

            //拿到url后可以保存到数据库进行操作
            //模拟添加学生数据
            Student stu = new Student();
            stu.setSid("37");
            stu.setSname("考虑考虑");
            stu.setSage("2022-01-06");
            stu.setSsex("女");
            stu.setUrl(url);

            service.add(stu);
            return new Result(true, ResultCode.DERIVE__SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, ResultCode.DERIVE__FAIL);
        }
    }

    /**
     * fastdfs删除文件
     * @param pdfFile
     * @return
     */
    @RequestMapping("/delPDF")
    @ResponseBody
    public Result delPDF(@RequestParam("pdfFile") String pdfFile) {
        //1.先判断参数是否为空
        //2.把file带进数据库中查看是否被引用
        //3.删除fastdfs中的图片(根据路径进行删除)
        if (pdfFile != null) {
            String[] split = pdfFile.split(fileUrl);
            dfsClientUtil.delFile(split[1]);
            return new Result(true, ResultCode.DELETE__SUCCESS);
        }
        return new Result(false, ResultCode.DERIVE__FAIL);
    }




}
