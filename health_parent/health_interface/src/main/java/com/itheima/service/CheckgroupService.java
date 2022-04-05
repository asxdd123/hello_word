package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

public interface CheckgroupService {
    //带条件分页查询
    PageResult findPage(QueryPageBean queryPageBean);

    //新增
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    // 根据id查数据
    CheckGroup findById(Integer id);

    // 查询检查项与检查组关联关系
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    // 编辑检查组
    void edit(CheckGroup group, Integer[] checkitemIds);

    // 删除
    void delete(Integer id);

    List<CheckGroup> findAll();

}
