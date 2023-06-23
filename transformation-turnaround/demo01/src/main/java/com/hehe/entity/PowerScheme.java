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
 * 
 * </p>
 *
 * @author 就不告诉你
 * @since 2023-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("power_scheme")
public class PowerScheme implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 电站名称
     */
    @TableField("station_name")
    private String stationName;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 间隔名称
     */
    @TableField("interval_name")
    private String intervalName;

    /**
     * 缺陷数量
     */
    @TableField("defect_sum")
    private String defectSum;

    /**
     * 隐患数量
     */
    @TableField("hazard_sum")
    private String hazardSum;


}
