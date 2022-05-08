package com.helllo.demo.pojo;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@ExcelTarget("region")
@TableName("region")
public class RegionEntity {

    @Excel(name = "主键ID",isImportField = "true_st")
    @TableId("id")
    private Integer id;

    @Excel(name = "区域id",isImportField = "true_st",width = 25)
    @TableField("region_id")
    private String regionid;

    @Excel(name = "区域编码",isImportField = "true_st",width = 25)
    @TableField("region_code")
    private String regioncode;

    @Excel(name = "区域名称",isImportField = "true_st",width = 40)
    @TableField("region_name")
    private String regionname;

    @Excel(name = "父节点id",isImportField = "true_st")
    @TableField("parent_id")
    private String parentid;

    @Excel(name = "创建数据时间",isImportField = "true_st",width = 35,format = "yyyy-MM-dd")
    @TableField("created_time")
    private Date createdtime;

    @Excel(name = "更新数据时间",isImportField = "true_st",width = 35,format = "yyyy-MM-dd")
    @TableField("updated_time")
    private Date updatedtime;
}
