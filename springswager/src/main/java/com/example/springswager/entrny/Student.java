package com.example.springswager.entrny;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-10-30 22:31
 */

@Data
@TableName("student")   //数据表名称
public class Student {

    @TableId("sid")
    @ApiModelProperty("学生ID")    // 对属性进行简要说明
    private String sid;

    @TableField("sname")
    @ApiModelProperty("学生姓名")
    private String sname;

    @TableField("sage")
    @ApiModelProperty("学生生日")
    private Date sage;

    @TableField("ssex")
    @ApiModelProperty("学生性别")
    private String ssex;

    @TableField("url")
    @ApiModelProperty("学生附件路径")
    private String url;
}
