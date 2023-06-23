package com.hehe.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import static com.hehe.utils.ResultEnum.*;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2023-04-13 22:41
 */
//统计返回结果集
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 4580737268023862568L;

    private Integer code;

    private String msg;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public  Result() {
    }
    public  Result(Integer code){
        this.code = code;
    }

    //是否成功（自定义结果码为1000为成功）
    @JsonIgnore
    public boolean isSuccess(){
        return this.code == 1000;
    }


    //成功时引用

    public static <T>  Result<T> success(){
        return success(SUCCESS);
    }
    public static <T>  Result<T> success(T data){
        return success(SUCCESS,data);
    }
    public static <T>  Result<T> success(ResultEnum re){
        return success(re,null);
    }
    public static <T>  Result<T> success(ResultEnum re,T data){
        Integer code = re.getCode();
        String msg = re.getMsg();
        return success(code,msg,data);
    }
    public static <T>  Result<T> success(Integer code, String msg, T data){
         Result<T> result = new  Result<>(1000);
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }



    //失败时引用

    public static <T>  Result<T> error(){
        return error(ERROR);
    }
    public static <T>  Result<T> error(ResultEnum re){
        return error(re,null);
    }
    public static <T>  Result<T> error(String msg){
        return error(ERROR.getCode(),msg,null);
    }
    public static <T>  Result<T> error(T data){
        return error(ERROR,data);
    }
    public static <T>  Result<T> error(ResultEnum re, T data){
        Integer code = re.getCode();
        String msg = re.getMsg();
        return error(code,msg,data);
    }
    public static <T>  Result<T> error(Integer code , String msg, T data){
         Result<T> result = new  Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

}
