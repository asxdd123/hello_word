package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckItemDao {
    // 分页带条件查询
    Page<CheckItem> findPage(@Param("queryString") String queryString);

    // 新增
    void add(CheckItem checkItem);

    // 根据id查数据
    CheckItem findById(Integer id);

    // 编辑
    void edit(CheckItem checkItem);

    //查询当前检查项是否和检查组关联
    long findCountByCheckItemId(Integer id);

    //删除
    void deleteById(Integer id);

    // 查全部
    List<CheckItem> findAll();

}
