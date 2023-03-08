package com.hehe.springbooredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-11-13 15:53
 */
@RestController
@RequestMapping("/string")
public class StringController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 根据key集合批量删除
     * redisTemplate.delete(keys) //其中keys:Collection<K> keys
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void delete() {
        ArrayList<String> list = new ArrayList<>();
        list.add("str");
        list.add("age");
        list.add("baike");
        list.add("bbb");
        list.add("bitTest");
        list.add("ccc");
        list.add("count");
        list.add("k1");
        list.add("k2");
        list.add("k3");
        list.add("key");
        list.add("name");
        list.add("str");
        list.add("ttt");
        redisTemplate.delete(list);
    }


    /**
     * redisTemplate操作String类型常用方法
     */
    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public void Test(){


        //key不存在，设值
        redisTemplate.opsForValue().append("str", "hello");
//        redisTemplate.opsForValue().setIfAbsent("str", "hello");
        Object str = redisTemplate.opsForValue().get("str");
        System.out.println("str==" + str);     //str==hello
        //key存在，追加
        redisTemplate.opsForValue().append("str", " world");
        Object str1 = redisTemplate.opsForValue().get("str");
        System.out.println("str1==" + str1);    //str1==hello world



        //设置k,v
        redisTemplate.opsForValue().set("name", "张三");
        //取值
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name==" + name);   //name==张三


        //修改指定key的名称
        redisTemplate.rename("name","newName");
        Object newName = redisTemplate.opsForValue().get("newName");
        System.out.println("newName==" + newName);    //newName==张三

//        判断key是否存在，存在则修改key名,不存在则报错
//        redisTemplate.renameIfAbsent(oldKey,newKey);



        //判断是否有key所对应的值，有则返回true，没有则返回false
        Boolean name1 = redisTemplate.hasKey("name");
        Object name2 = redisTemplate.opsForValue().get("name");
        System.out.println("name2==" + name2);   //name2==null



        redisTemplate.opsForValue().set("bbb", "test");
        //若key不存在，设值
        redisTemplate.opsForValue().setIfAbsent("bbb", "test2");
        Object bbb = redisTemplate.opsForValue().get("bbb");
        System.out.println("bbb==" + bbb);//还是test



        //批量k,v设值
        Map<String, String> map = new HashMap<String, String>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        redisTemplate.opsForValue().multiSet(map);


        //批量取值
        List<String> keys = new ArrayList<String>();
        keys.add("k1");
        keys.add("k2");
        keys.add("k3");
        List<String> values = redisTemplate.opsForValue().multiGet(keys);
        System.out.println("values==" + values);    //values==[v1, v2, v3]

        //批量设值，若key不存在，设值
        //redisTemplate.opsForValue().multiSetIfAbsent(map);



        //设置k,v以及有效时长，TimeUnit为单位
        redisTemplate.opsForValue().set("ceshi", "bpf", 10, TimeUnit.SECONDS);
//        Thread.sleep(11000);
        Object ceshi = redisTemplate.opsForValue().get("ceshi");
        System.out.println("ceshi==" + ceshi);   //ceshi==bpf


        //根据key获取过期时间(以秒为单位返回剩余生存时间)
        Long expire = stringRedisTemplate.getExpire("ceshi");
        System.out.println("expire==" + expire);    //expire==10



        //根据key获取过期时间并换算成指定单位
        Long baike = stringRedisTemplate.getExpire("ceshi", TimeUnit.SECONDS);
        System.out.println("baike==" + baike);   //baike==9


        redisTemplate.opsForValue().set("key", "hello world");
        //从偏移量开始对给定key的value进行覆写，若key不存在，则前面偏移量为空
        redisTemplate.opsForValue().set("key", "redis", 6);
        Object key = redisTemplate.opsForValue().get("key");
        System.out.println("key==" + key);   //key==hello redis



        redisTemplate.opsForValue().set("name", "bpf");
        //设值并返回旧值，无旧值返回null
        Object andSet = redisTemplate.opsForValue().getAndSet("ttt", "calvin");
        System.out.println("andSet==" + andSet);   //andSet==null



        //自增操作，原子性。可以对long进行double自增，但不能对double进行long自增，会抛出异常
        redisTemplate.opsForValue().increment("age", 1);
        Object age = redisTemplate.opsForValue().get("age");
        System.out.println("age==" + age);   //age==1

        redisTemplate.opsForValue().increment("age", -1);
        Object age1 = redisTemplate.opsForValue().get("age");
        System.out.println("age1==" + age1);   //age1==0

        redisTemplate.opsForValue().increment("count", 1L);//增量为long
        Object count = redisTemplate.opsForValue().get("count");
        System.out.println("count==" + count);   //count==1

        redisTemplate.opsForValue().increment("count", 1.1);//增量为double
        Object count1 = redisTemplate.opsForValue().get("count");
        System.out.println("count1==" + count1);   //count1==2.10000000000000009




        redisTemplate.opsForValue().set("ccc", "hello world");
        //value的长度
        Long size = redisTemplate.opsForValue().size("ccc");
        System.out.println("size==" + size);//size==11



        redisTemplate.opsForValue().set("bitTest", "a");
        // 'a' 的ASCII码是 97  转换为二进制是：01100001
        // 'b' 的ASCII码是 98  转换为二进制是：01100010
        // 'c' 的ASCII码是 99  转换为二进制是：01100011

        //因为二进制只有0和1，在setbit中true为1，false为0，因此我要变为'b'的话第六位设置为1，第七位设置为0
        redisTemplate.opsForValue().setBit("bitTest", 6, true);
        redisTemplate.opsForValue().setBit("bitTest", 7, false);
        Object bitTest = redisTemplate.opsForValue().get("bitTest");
        System.out.println("bitTest==" + bitTest);   //bitTest==b


        //判断offset处是true1还是false0
        Boolean bitTest1 = redisTemplate.opsForValue().getBit("bitTest", 7);
        System.out.println("bitTest1==" + bitTest1);   //bitTest1==false



        //向redis里存入数据和设置缓存时间
        stringRedisTemplate.opsForValue().set("baike", "100", 60 * 10, TimeUnit.SECONDS);
        //根据key获取缓存中的val
        String baike1 = stringRedisTemplate.opsForValue().get("baike");
        System.out.println("baike1==" + baike1);   //baike1==100



        //根据key获取过期时间
        Long baike2 = stringRedisTemplate.getExpire("baike");
        System.out.println("baike2==" + baike2);   //baike2==600



        //根据key获取过期时间并换算成指定单位
        Long baike3 = stringRedisTemplate.getExpire("baike", TimeUnit.SECONDS);
        System.out.println("baike3==" + baike3);   //baike3==599



        //val +1
        stringRedisTemplate.boundValueOps("baike").increment(1);
        Object baike4 = redisTemplate.opsForValue().get("baike");
        System.out.println("baike4==" + baike4);   //baike4==101
        //val做-1操作
        stringRedisTemplate.boundValueOps("baike").increment(-1);
        Object baike5 = redisTemplate.opsForValue().get("baike");
        System.out.println("baike5==" + baike5);   //baike5==100



        //根据key删除缓存
        Boolean baike6 = stringRedisTemplate.delete("baike");
        System.out.println("baike6==" + baike6);   //baike6==true
        Object baike7 = redisTemplate.opsForValue().get("baike");
        System.out.println("baike7==" + baike7);   //baike7==null



        //删除单个key值
        Boolean ccc = redisTemplate.delete("ccc");
        System.out.println("ccc==" + ccc);   //ccc==true



        //检查key是否存在，返回boolean值
        Boolean baike8 = stringRedisTemplate.hasKey("baike");
        System.out.println("baike8==" + baike8);   //baike8==false



        //向指定key中存放set集合
        stringRedisTemplate.opsForSet().add("baike", "1", "2", "3");
        DataType type = redisTemplate.type("baike");
        //获取key的数据类型
        System.out.println("type==" + type);   //type==SET
        Object baike9 = redisTemplate.opsForSet().members("baike");
        System.out.println("baike9==" + baike9);   //baike9==[1, 2, 3]



        //设置过期时间
        stringRedisTemplate.expire("baike", 1000, TimeUnit.MILLISECONDS);



        //根据key查看集合中是否存在指定数据
        Boolean baike10 = stringRedisTemplate.opsForSet().isMember("baike", "1");
        System.out.println("baike10==" + baike10);   //baike10==true



        //根据key获取set集合
        Set<String> baike11 = stringRedisTemplate.opsForSet().members("baike");
        System.out.println("baike11==" + baike11);   //baike11==[1, 2, 3]


        //验证有效时间
        Long expire2 = redisTemplate.boundHashOps("baike").getExpire();
        System.out.println("expire2==" + expire2);   //expire2==1

        /**
         * 从redis中获取key对应的过期时间;
         * 如果该值有过期时间，就返回相应的过期时间;
         * 如果该值没有设置过期时间，就返回-1;
         * 如果没有该值，就返回-2;
         */
        Long aLong = redisTemplate.opsForValue().getOperations().getExpire("baike");
        System.out.println("aLong==" + aLong);   //aLong==1

//        设置指定key永久保护,移除存活时间
//        redisTemplate.persist(key);
    }
}
