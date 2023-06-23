package com.hehe.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 多数据源表
 * </p>
 *
 * @author 就不告诉你
 * @since 2023-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("multi_data")
public class MultiData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 电站名称
     */
    @TableField("station_name")
    private String stationName;

    /**
     * 电压等级
     */
    @TableField("voltage_level")
    private String voltageLevel;

    /**
     * 设备等级
     */
    @TableField("equipment_rating")
    private String equipmentRating;

    /**
     * 设备类型
     */
    @TableField("device_type")
    private String deviceType;

    /**
     * 设备名称
     */
    @TableField("device_name")
    private String deviceName;

    /**
     * 投运时间
     */
    @TableField("operation_time")
    private LocalDateTime operationTime;

    /**
     * 投运状态
     */
    @TableField("operation_status")
    private String operationStatus;

    /**
     * 生产厂家
     */
    @TableField("manu_facturer")
    private String manuFacturer;

    /**
     * 有无报告
     */
    @TableField("is_report")
    private String isReport;

    /**
     * 有无工作票
     */
    @TableField("is_workticket")
    private String isWorkticket;

    /**
     * 有无历史缺陷
     */
    @TableField("is_historical")
    private String isHistorical;


}
