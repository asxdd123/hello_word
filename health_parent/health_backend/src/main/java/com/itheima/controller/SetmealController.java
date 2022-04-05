package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService service;
    @Autowired
    private JedisPool jedisPool; //使用JedisPool来操作redis服务

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
     * 新增
     *
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        try {
            service.add(setmeal, checkgroupIds);
        } catch (Exception e) {
            //新增失败
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        //新增成功
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Integer id) {
        try {
            Setmeal setmeal = service.findById(id);
            System.out.println(setmeal);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    /**
     * 查看套餐与检查组关联关系
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/findCheckItemIdsByCheckGroupId", method = RequestMethod.GET)
    public Result findCheckItemIdsByCheckGroupId(@RequestParam("id") Integer id) {
        try {
            List<Integer> list = service.findCheckItemIdsByCheckGroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 编辑
     *
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result edit(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        System.out.println(setmeal);
        try {
            service.edit(setmeal, checkgroupIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
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
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }

    /**
     * 图片上传到七牛云
     *
     * 前面我们已经完成了文件上传，将图片存储在了七牛云服务器中。但是这个过程存在一个问题，就是如果用户只上传了图片而没有
     * 最终保存套餐信息到我们的数据库，这时我们上传的图片就变为了垃圾图片。对于这些垃圾图片我们需要定时清理来释放磁盘空间。
     * 这就需要我们能够区分出来哪些是垃圾图片，哪些不是垃圾图片。如何实现呢？
     * 方案就是利用redis来保存图片名称，具体做法为：
     * 1、当用户上传图片后，将图片名称保存到redis的一个Set集合中，例如集合名称为setmealPicResources (在controller中)
     * 2、当用户添加套餐后，将图片名称保存到redis的另一个Set集合中，例如集合名称为setmealPicDbResources (在servieimpl实现类中)
     * 3、计算setmealPicResources集合与setmealPicDbResources集合的差值，结果就是垃圾图片的名称集合，清理这些图片即可
     *
     * @param imgFile
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        //获取原始文件名  aaa.jpg
        String originalFilename = imgFile.getOriginalFilename();
        int lastIndexOf = originalFilename.lastIndexOf(".");
        //获取文件后缀  .jpg
        String suffix = originalFilename.substring(lastIndexOf);
        //使用UUID随机产生文件名称，防止同名文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix; // fileName: 随机名.jpg
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
            //将上传图片名称存入Redis，基于Redis的Set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            //图片上传成功
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName); //将文件名返回前端
        } catch (Exception e) {
            e.printStackTrace();
            //图片上传失败
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }


}
