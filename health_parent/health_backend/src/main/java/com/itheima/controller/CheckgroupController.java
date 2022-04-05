package com.itheima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckgroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckgroupController {

    @Reference
    private CheckgroupService service;

    /**
     * 分页带条件查询
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult result = service.findPage(queryPageBean);
        return result;
    }

    /**
     * 添加检查组合，同时需要设置检查组合和检查项的关联关系
     *
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            service.add(checkGroup, checkitemIds);
        } catch (Exception e) {
            //新增失败
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        //新增成功
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 根据id查检查组
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Integer id) {
        try {
            CheckGroup group = service.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, group);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 查询检查项与检查组关联关系
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/findCheckItemIdsByCheckGroupId", method = RequestMethod.GET)
    public Result findCheckItemIdsByCheckGroupId(@RequestParam("id") Integer id) {
        try {
            List<Integer> checkitemIds = service.findCheckItemIdsByCheckGroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    /**
     * 编辑
     *
     * @param group
     * @param checkitemIds
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result edit(@RequestBody CheckGroup group, Integer[] checkitemIds) {
        try {
            service.edit(group, checkitemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result delete(@RequestParam("id") Integer id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    /**
     * 查所有
     *
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAll() {
        try {
            List<CheckGroup> list = service.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
    }
}
