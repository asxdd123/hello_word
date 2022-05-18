package com.example.excptionto.until;


/**
 * 统一异常处理返回枚举类
 */
public enum ResponseEnum {
    SUCCESS(200,"成功"),

    ERROR(500,"服务器内部错误"),

    BAD_SQL_EXCEPTION(-101,"SQL语法错误"),

    NULL_POINT_EXCEPTION(-102,"空指针异常"),

    EXCEPTION(-222,"异常");



    private Integer code;
    private String  message;


    ResponseEnum() {
    }

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
