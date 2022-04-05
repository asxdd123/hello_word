package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    PageResult findPage(QueryPageBean queryPageBean);

    void add(Setmeal setmeal, Integer[] checkgroupIds);

    Setmeal findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(Setmeal setmeal, Integer[] checkgroupIds);

    void delete(Integer id);
}
