package com.java.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author 一条不会飞的咸鱼
 * @Date 2023/12/6 22:15
 * @Version 1.0
 */
@Data
public class UserVO {
    private String name;
    private String age;
    private String birthday;
    private String phone;

}
