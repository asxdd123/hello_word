package com.hehe.utils;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2023-04-13 22:44
 */
public enum ResultEnum {
    SUCCESS(1000, "成功"),
    ERROR(9999,"失败");


    private Integer code;
    private String msg;

    ResultEnum(Integer code , String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
