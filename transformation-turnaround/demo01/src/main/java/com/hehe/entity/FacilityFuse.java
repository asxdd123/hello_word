package com.hehe.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 多源数据融合管理中设备信息表
 * </p>
 *
 * @author 就不告诉你
 * @since 2023-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("facility_fuse")
public class FacilityFuse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Integer id;

    /**
     * 电站名称
     */
    @TableField("name_plant")
    private String namePlant;

    /**
     * 电压等级
     */
    @TableField("voltage_classes")
    private String voltageClasses;

    /**
     * 设备等级
     */
    @TableField("equipment_level")
    private String equipmentLevel;

    /**
     * 设备类型
     */
    @TableField("equipment_type")
    private String equipmentType;

    /**
     * 设备名称
     */
    @TableField("equipment_name")
    private String equipmentName;

    /**
     * 投运时间
     */
    @TableField("commiss_time")
    private LocalDate commissTime;

    /**
     * 投运状态
     */
    @TableField("commiss_status")
    private String commissStatus;

    /**
     * 生产厂家
     */
    @TableField("manufacturer")
    private String manufacturer;

    /**
     * 有无报告
     */
    @TableField("report_not")
    private String reportNot;

    /**
     * 有无工作票
     */
    @TableField("work_ticket_not")
    private String workTicketNot;

    /**
     * 有无历史缺陷
     */
    @TableField("flaw_not")
    private String flawNot;


}
