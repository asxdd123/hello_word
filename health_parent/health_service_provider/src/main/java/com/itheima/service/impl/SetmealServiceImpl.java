package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConstant;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;

@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao dao;

    @Autowired
    private JedisPool jedisPool;  //使用JedisPool来操作redis服务

    /**
     * 分页查
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
        Page<Setmeal> list = dao.findPage(queryString);
        return new PageResult(list.getTotal(), list.getResult());
    }

    /**
     * 添加套餐
     *
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //添加套餐
        dao.add(setmeal);
        //设置套餐与检查组的关联关系
        setCheckGroupAndSetmeal(setmeal.getId(), checkgroupIds);
        //将图片名称保存到Redis
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());

    }

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(Integer id) {
        return dao.findById(id);
    }

    /**
     * 查看套餐与检查组关联关系
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return dao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 编辑套餐
     *
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void edit(Setmeal setmeal, Integer[] checkgroupIds) {
        //先清除套餐与检查组关联关系(中间表)
        dao.deleteAssociation(setmeal.getId());
        //修改套餐信息
        dao.edit(setmeal);
        //向中间表插入数据（建立检查组和套餐关联关系）
        setCheckGroupAndSetmeal(setmeal.getId(), checkgroupIds);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        //先清除套餐与检查组关联关系(中间表)
        dao.deleteAssociation(id);
        //删除套餐
        dao.delete(id);
    }

    /**
     * 设置套餐与检查组多对多关系
     *
     * @param id
     * @param checkgroupIds
     */
    private void setCheckGroupAndSetmeal(Integer id, Integer[] checkgroupIds) {
        if (id != null && checkgroupIds != null) {
            for (Integer checkgroupId : checkgroupIds) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("setmeal_id", id);
                map.put("checkgroup_id", checkgroupId);
                dao.setCheckGroupAndSetmeal(map);
            }
        }
    }
}
