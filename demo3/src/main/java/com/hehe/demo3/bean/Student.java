package com.hehe.demo3.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Sid")
    private String Sid;

    @TableField("Sname")
    private String Sname;

    @TableField("Sage")
    private String Sage;

    @TableField("Ssex")
    private String Ssex;

}
