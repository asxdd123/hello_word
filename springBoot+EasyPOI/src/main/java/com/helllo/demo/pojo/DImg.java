package com.helllo.demo.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 图片实体类
 */
@Data
@ExcelTarget("图片表")
@TableName("d_img")
public class DImg {

    @Excel(name = "主键ID")
    @TableId("id")
    private Integer id;

    @Excel(name = "产品图片ID")
    @TableField("product_id")
    private Integer productid;

    @Excel(name = "图片",width = 60)
    @TableField("img")
    private String img;

    @Excel(name = "创建时间",format = "yyyy-MM-dd")
    @TableField("create_time")
    private Date createtime;


}
