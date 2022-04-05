package com.example.login.exception1.impl;

import com.example.login.exception1.NoFoundExcepiton;
import com.example.login.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestControllerAdvice
public class GlobalException {
    private static Logger LOGGER = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler
    public ResponseResult processException(Exception ex, HttpServletRequest request, HttpServletResponse response){
        ex.printStackTrace();

        if(ex instanceof MissingServletRequestParameterException){
            return new ResponseResult(400, ex.getMessage(), null);
        }
        if(ex instanceof NoFoundExcepiton){

            LOGGER.error("======="+ex.getMessage()+"=======");
            return new ResponseResult(401, "无法找到相应数据！", null);

        }

        return new ResponseResult(500, ex.getMessage(), null);

    }

}
