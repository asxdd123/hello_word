package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    //    分页带条件查询
    PageResult findPage(QueryPageBean queryPageBean);

    //  新增
    void add(CheckItem checkItem);

    // 根据id查数据
    CheckItem findById(Integer id);

    // 编辑
    void edit(CheckItem checkItem);

    // 删除
    void delete(Integer id);

    // 查全部
    List<CheckItem> findAll();

}
