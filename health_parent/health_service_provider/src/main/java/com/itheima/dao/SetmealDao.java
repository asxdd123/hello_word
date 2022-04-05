package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SetmealDao {
    Page<Setmeal> findPage(@Param("queryString") String queryString);

    void add(Setmeal setmeal);

    void setCheckGroupAndSetmeal(HashMap<String, Object> map);

    Setmeal findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void deleteAssociation(Integer id);

    void edit(Setmeal setmeal);

    void delete(Integer id);
}
