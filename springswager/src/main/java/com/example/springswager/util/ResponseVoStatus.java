package com.example.springswager.util;

import lombok.Data;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-10-30 22:58
 */

public enum ResponseVoStatus  {

    SUCCESS("success",200),
    ERROR("error",000);

    private String message;
    private Integer code;

    ResponseVoStatus(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

}

