package com.java.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author 一条不会飞的咸鱼
 * @Date 2023/12/6 22:17
 * @Version 1.0
 */
@Data
public class UserDTO {
    private String realName;
    private String realAge;
    private Date realBirthday;
    private String photo;
}
