package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckgroupDao {
    // 分页查询
    Page<CheckGroup> findPage(@Param("queryString") String queryString);

    // 新增
    void add(CheckGroup checkGroup);

    // 设置检查组合和检查项的关联关系
    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    // 根据id查数据
    CheckGroup findById(Integer id);

    // 查询检查项与检查组关联关系
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    // 根据检查组id删除中间表数据（清理原有关联关系）
    void deleteAssociation(Integer id);

    // 更新检查组基本信息
    void edit(CheckGroup group);

    // 删除
    void delete(Integer id);
}
