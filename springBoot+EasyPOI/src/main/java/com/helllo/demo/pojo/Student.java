package com.helllo.demo.pojo;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("student")
@ExcelTarget("Student")
public class Student  {

//    @Excel(name = "id")
    @TableId("Sid")
    private String sid;

    @Excel(name = "姓名")
    @TableField("Sname")
    private String sname;

    @Excel(name = "生日",format = "yyyy-MM-dd")
    @TableField("Sage")
    private Date sage;

    @Excel(name = "性别")
    @TableField("Ssex")
    private String ssex;

    @Excel(name = "路径")
    @TableField("url")
    private String url;


}
