package com.hh.demo.TreeBean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 部门Tree结构实体类
 */
@Data
@TableName("sys_dept")
public class DeptEmtity implements Serializable {
    /**
     * 部门id
     */
    @TableId(value="dept_id",type= IdType.AUTO)
    private Integer deptId;
    /**
     * 父Id
     */
    private Integer parentId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序编号
     */
    private String orderNum;
    /**
     * 删除标识
     */
    private String delFlag;
    /**
     * 子节点
     */
    private List<DeptEmtity> treeNode;


}
