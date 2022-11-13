package com.hehe.springbooredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-10-26 20:41
 */

@RestController
@RequestMapping("/hash")
public class HashController {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * redisTemplate操作hash类型常用方法
     */
    @RequestMapping("/hash")
    public void testHash () {
        //新增hashMap值
        //
        //var1 为Redis的key
        //var2 为key对应的map值的key
        //var3 为key对应的map值的值
        //var2相同替换var3
        redisTemplate.opsForHash().put("hashValue","map1","value1");
        redisTemplate.opsForHash().put("hashValue","map2","value2");


        //如果key对应的map不存在，则新增到map中，存在则不新增也不覆盖
        redisTemplate.opsForHash().putIfAbsent("hashValue", "map3", "value3");


        //直接以map集合的方式添加key对应的值
        //
        //map中key已经存在，覆盖替换
        //map中key不存在，新增
        HashMap map = new HashMap();
        map.put("map4","value4");
        map.put("map5","value5");
        redisTemplate.opsForHash().putAll("hashValue",map);


        //获取key对应的map中，key为var2的map的对应的值
        Object o = redisTemplate.opsForHash().get("hashValue", "map1");
        System.out.println("o = " + o);


        //获取key对应的所有map键值对
        Map hashValue = redisTemplate.opsForHash().entries("hashValue");
        System.out.println("hashValue = " + hashValue);

        //获取key对应的map中所有的键
        Set set = redisTemplate.opsForHash().keys("hashValue");
        System.out.println("hashValue = " + set);

        //获取key对应的map中所有的值
        List list = redisTemplate.opsForHash().values("hashValue");
        System.out.println("hashValue = " + list);

        //判断key对应的map中是否有指定的键
        Boolean aBoolean = redisTemplate.opsForHash().hasKey("hashValue", "map1");
        System.out.println("aBoolean = " + aBoolean);

        Long size = redisTemplate.opsForHash().size("hashValue");
        System.out.println("hashValue = " + size);

        //以集合的方式获取这些键对应的map
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("map1");
        arrayList.add("map2");
        List multiGet = redisTemplate.opsForHash().multiGet("hashValue", arrayList);
        System.out.println("hashValue = " + multiGet);

        //获取指定key对应的map集合中，指定键对应的值的长度
        Long aLong = redisTemplate.opsForHash().lengthOfValue("hashValue", "map1");
        System.out.println("aLong = " + aLong);

        // 使key对应的map中，键var2对应的值以long1自增
        Long increment = redisTemplate.opsForHash().increment("hashValue", "map7", 1);
        System.out.println("increment = " + increment);

        //使key对应的map中，键var2对应的值以double类型d1自增
        Double aDouble = redisTemplate.opsForHash().increment("hashValue", "map8", 1.2);
        System.out.println("increment = " + aDouble);

        //匹配获取键值对
        //
        //ScanOptions.NONE为获取全部键对
        //ScanOptions.scanOptions().match(“map1”).build()，匹配获取键位map1的键值对,不能模糊匹配
        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan("hashValue", ScanOptions.scanOptions().match("map1").build());
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        }

        //删除key对应的map中的键值对
        Long delete = redisTemplate.opsForHash().delete("hashValue", "map1", "map2");
        System.out.println("delete = " + delete);

        //map中存储对象、对象集合时最好转为JSON字符串，容易解析
        //
        //map中键值对都可以存为对象、对象集合JSON字符串，具体看实际应用存储
//        List<MPEntity> list = mpService.list();
//        redisTemplate.opsForHash().put("hashValue",JSON.toJSONString(list),JSON.toJSONString(list));
    }
}
