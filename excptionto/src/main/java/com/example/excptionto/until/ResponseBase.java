package com.example.excptionto.until;


/**
 * 统一对象返回
 */
public class ResponseBase {

    private Integer code;
    private String  msg;
    private Object  data;
    private int     count;

    public ResponseBase() {}

    /**
     * 成功/失败返回，无数据返回
     * @param code
     * @param msg
     */
    public ResponseBase(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功，有数据返回
     * @param code
     * @param msg
     * @param data
     */
    public ResponseBase(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功有数据返回
     * @param code
     * @param msg
     * @param data
     * @param count
     */
    public ResponseBase(Integer code, String msg, Object data, int count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    /**
     * 成功返回
     * @return
     */
    public static ResponseBase success(){
        ResponseBase response = new ResponseBase();
        response.setCode(ConstantCode.SUCCESS_CODE);
        response.setMsg(ConstantCode.SUCCESS_MSG);
        return response;
    }

    /**
     * 有数据的成功返回
     * @return
     */
    public static ResponseBase success(Object data, int count){
        ResponseBase response = new ResponseBase();
        response.setCode(ConstantCode.SUCCESS_CODE);
        response.setMsg(ConstantCode.SUCCESS_MSG);
        response.setData(data);
        response.setCount(count);
        return response;
    }

    /**
     * 有数据的成功返回
     * @return
     */
    public static ResponseBase success(Object data){
        ResponseBase response = new ResponseBase();
        response.setCode(ConstantCode.SUCCESS_CODE);
        response.setMsg(ConstantCode.SUCCESS_MSG);
        response.setData(data);
        return response;
    }

    /**
     * 有数据的成功返回
     * @return
     */
    public static ResponseBase success(Object data, String msg){
        ResponseBase response = new ResponseBase();
        response.setCode(ConstantCode.SUCCESS_CODE);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    /**
     * 失败情况下返回
     * @return
     */
    public static ResponseBase error(){
        ResponseBase response = new ResponseBase();
        response.setCode(ConstantCode.ERROR_CODE);
        response.setMsg(ConstantCode.ERROR_MSG);
        return response;
    }

    /**
     * 错误返回
     * @param code
     * @param message
     * @return
     */
    public static ResponseBase error(Integer code,String message){
        ResponseBase r = new ResponseBase();
        if(StringBusinessUtil.isNullOrEmpty(code) || "0".equals(String.valueOf(code)) || code <= 0){
            r.setCode(ResponseEnum.ERROR.getCode());
        }else{
            r.setCode(code);
        }
        if (StringBusinessUtil.isNullOrEmpty(message)){
            r.setMsg(ResponseEnum.ERROR.getMessage());
        }else{
            r.setMsg(message);
        }
        return r;
    }



    /**
     * 连缀输出
     * @param message
     * @return
     */
    public ResponseBase message(String message){
        this.setMsg(message);
        return this;
    }


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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

