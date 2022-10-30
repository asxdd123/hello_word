package com.example.springswager.controller;

import com.example.springswager.entrny.Student;
import com.example.springswager.serve.StudentService;
import com.example.springswager.util.ResponseVo;
import com.example.springswager.util.ResponseVoStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-10-30 22:31
 */

@Api(value = "学生管理相关接口", tags = "channel", description = "学生管理相关接口API")   //修饰整个类，描述Controller的作用
@RestController
@RequestMapping("/hello")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @ApiOperation("查询所有学生信息")   // 描述一个类的一个方法，或者说一个接口
    @RequestMapping(method = RequestMethod.POST, value = "/selectList")
    public List<Student> selectList() {
        return studentService.selectList();
    }


    @ApiOperation("新增学生信息")
    @RequestMapping(method = RequestMethod.POST, value = "/insetStudent")
    @ApiImplicitParam(name = "student", value = "所添加的学生", dataTypeClass = Student.class)   //一个请求参数的描述信息
    public ResponseVo insetStudent(@RequestBody Student student) {
        try {
            studentService.save(student);
            return new ResponseVo(ResponseVoStatus.SUCCESS.getMessage(), ResponseVoStatus.SUCCESS.getCode(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVo(ResponseVoStatus.ERROR.getMessage(), ResponseVoStatus.ERROR.getCode(), null);
        }
    }


    @ApiOperation("修改学生信息")
    @RequestMapping(method = RequestMethod.POST, value = "/updateStudent")
    @ApiImplicitParam(name = "student", value = "所修改的学生", dataTypeClass = Student.class)   //一个请求参数的描述信息
    public ResponseVo updateStudent(@RequestBody Student student) {
        try {
            studentService.updateById(student);
            return new ResponseVo(ResponseVoStatus.SUCCESS.getMessage(), ResponseVoStatus.SUCCESS.getCode(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVo(ResponseVoStatus.ERROR.getMessage(), ResponseVoStatus.ERROR.getCode(), null);
        }
    }


    @ApiOperation("根据id删除学生信息")
    @RequestMapping(method = RequestMethod.GET, value = "/deleteStudentById")
    @ApiImplicitParams({   //注意使用@ApiImplicitParams的使用里面有一个大括号
            @ApiImplicitParam(required = true, name = "id", value = "所删除的学生的ID", dataType = "String"),   //一个请求参数的描述信息
            @ApiImplicitParam(required = false, name = "name", value = "所删除的学生的姓名", dataType = "String")   //一个请求参数的描述信息
    })
    public ResponseVo deleteStudentById(@RequestParam String id, @RequestParam String name) {
        try {
            studentService.removeById(id);
            return new ResponseVo(ResponseVoStatus.SUCCESS.getMessage(), ResponseVoStatus.SUCCESS.getCode(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVo(ResponseVoStatus.ERROR.getMessage(), ResponseVoStatus.ERROR.getCode(), null);
        }
    }



    @ApiOperation("根据id查询学生信息")
    @RequestMapping(method = RequestMethod.GET, value = "/queryStudentById/{id}/{name}")
    @ApiImplicitParams({   //注意使用@ApiImplicitParams的使用里面有一个大括号
            @ApiImplicitParam(required = true, name = "id", value = "所查找的学生的ID", dataType = "String"),   //一个请求参数的描述信息
            @ApiImplicitParam(required = true, name = "name", value = "所查找的学生的姓名", dataType = "String")   //一个请求参数的描述信息
    })
    public ResponseVo queryStudentById(@PathVariable("id") String id, @PathVariable("name") String name) {
        Student student = null;
        try {
             student = studentService.getById(id);
            return new ResponseVo(ResponseVoStatus.SUCCESS.getMessage(), ResponseVoStatus.SUCCESS.getCode(), student);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVo(ResponseVoStatus.ERROR.getMessage(), ResponseVoStatus.ERROR.getCode(), student);
        }
    }

}
