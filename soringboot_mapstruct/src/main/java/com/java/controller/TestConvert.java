package com.java.controller;

import com.java.entity.UserDTO;
import com.java.mapper.MapstructConvetor;
import com.java.pojo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author 一条不会飞的咸鱼
 * @Date 2023/12/6 23:48
 * @Version 1.0
 */
@RestController
@RequestMapping("/hello")
public class TestConvert {

    @Autowired
    private MapstructConvetor mapstructConvetor;


    @RequestMapping("/aa")
    public UserDTO test(){
        UserVO vo = new UserVO();
        vo.setName("张三");
        vo.setAge("20");
        vo.setPhone("123");
        vo.setBirthday("2023-01-01");

//        UserDTO userDTO = MapstructConvetor.INSTANCE.toProgramerDto(vo);   //以mapper工厂调用转换方法
        UserDTO dto = mapstructConvetor.toProgramerDto(vo);   //使用注入的形式获取方法
        System.out.println(dto);
        return dto;
    }
}
