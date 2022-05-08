package com.helllo.demo.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("login")
@ExcelTarget("Login")
@Data
public class Login implements Serializable {
//    @Excel(name = "主键ID")
    private Integer id;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "密码")
    private String password;
}
