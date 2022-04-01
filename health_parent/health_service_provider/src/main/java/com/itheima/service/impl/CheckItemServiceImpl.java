package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao mapper;

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
        //mybatis框架提供的分页助手来实现分页
        PageHelper.startPage(page, size);
        Page<CheckItem> list = mapper.findPage(queryString);

        return new PageResult(list.getTotal(), list.getResult());
    }

    /**
     * 编辑
     *
     * @param checkItem
     */
    @Override
    public void edit(CheckItem checkItem) {
        mapper.edit(checkItem);
    }

    /**
     * 根据id查数据
     *
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(Integer id) {
        CheckItem checkItem = mapper.findById(id);
        return checkItem;
    }

    /**
     * 新增
     *
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        mapper.add(checkItem);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        //查询当前检查项是否和检查组关联
        long count = mapper.findCountByCheckItemId(id);
        if (count > 0) {
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        mapper.deleteById(id);
    }

    /**
     * 查全部
     *
     * @return
     */
    @Override
    public List<CheckItem> findAll() {
        return mapper.findAll();
    }
}
