package com.example.login.utils;


import lombok.Data;

/**
 * 返回值实体类
 * <p>Title: ResponseResult</p>
 * @author  Liyan
 * @date    2018年1月11日 下午5:23:41
**/

@Data
public class ResponseResult {
    private Integer code;
    private String msg;
    private Object data;

    public ResponseResult(Integer code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
