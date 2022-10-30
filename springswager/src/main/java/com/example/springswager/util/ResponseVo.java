package com.example.springswager.util;

import lombok.Data;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-10-30 22:55
 * 返回结果封装类
 */
@Data
public class ResponseVo {
//    private ResponseVoStatus status;   //消息响应码
    private String message;
    private Integer code;
    private Object date;   //返回的数据

    public ResponseVo(String message, Integer code, Object date) {
        this.message = message;
        this.code = code;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public Object getDate() {
        return date;
    }
}
