package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckgroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = CheckgroupService.class)
@Transactional
public class CheckgroupServiceImpl implements CheckgroupService {

    @Autowired
    private CheckgroupDao mapper;

    /**
     * 分页带条件查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer page = queryPageBean.getCurrentPage();
        Integer size = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(page, size);
        Page<CheckGroup> list = mapper.findPage(queryString);

        return new PageResult(list.getTotal(), list.getResult());
    }

    /**
     * 添加检查组合，同时需要设置检查组合和检查项的关联关系
     *
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        mapper.add(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }

    /**
     * 根据id查数据
     *
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(Integer id) {
        return mapper.findById(id);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        //删除检查组和检查项关联关系
        mapper.deleteAssociation(id);
        //删除检查组
        mapper.delete(id);
    }

    /**
     * 查所有
     * @return
     */
    @Override
    public List<CheckGroup> findAll() {
        return mapper.findAll();
    }

    /**
     * 编辑检查组，同时需要更新和检查项的关联关系
     *
     * @param group
     * @param checkitemIds
     */
    @Override
    public void edit(CheckGroup group, Integer[] checkitemIds) {
        //根据检查组id删除中间表数据（清理原有关联关系）
        mapper.deleteAssociation(group.getId());

        //向中间表(t_checkgroup_checkitem)插入数据（建立检查组和检查项关联关系）
        setCheckGroupAndCheckItem(group.getId(), checkitemIds);

        //更新检查组基本信息
        mapper.edit(group);
    }

    /**
     * 查询检查项与检查组关联关系
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return mapper.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 设置检查组合和检查项的关联关系
     *
     * @param checkGroupId
     * @param checkitemIds
     */
    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("checkgroup_id", checkGroupId);
                map.put("checkitem_id", checkitemId);
                mapper.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
