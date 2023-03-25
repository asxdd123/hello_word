package com.hehe.demo3.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Sage;

    @TableField("Ssex")
    private String Ssex;

}
