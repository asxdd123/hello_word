package com.helllo.demo.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 产品实体类
 */
@Data
@TableName("d_product")
@ExcelTarget("产品表")
public class Dproduct implements Serializable {

    @Excel(name = "产品ID")
    @TableId("id")
    private Integer id;

    @Excel(name = "产品名称",width = 30,needMerge = true)
    @TableField("product_name")
    private String productName;

    @Excel(name = "产品介绍",width = 40,needMerge = true)
    @TableField("product_introduction")
    private String productIntroduction;

    @Excel(name = "产品ID",needMerge = true)
    @TableField("product_category")
    private Integer productCategory;

    @Excel(name = "产品状态",width = 20,needMerge = true)
    @TableField("product_status")
    private Integer productStatus;

    @Excel(name = "创建时间",format = "yyyy-MM-dd",width = 30,needMerge = true)
    @TableField("create_time")
    private Date createTime;

    @ExcelCollection(name = "图片")
    @TableField(exist = false)
    private List<DImg> children;     // 用于存放图片集合


}
