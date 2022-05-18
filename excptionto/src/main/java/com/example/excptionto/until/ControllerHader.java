package com.example.excptionto.until;


import com.example.excptionto.exception.BusinessException;
import com.example.excptionto.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 自定义异常处理类
 */
@ControllerAdvice  //声明后该类就会被加载成异常处理器   全局捕捉controller异常    比如service层发生异常他会往上一级抛,抛给controller层
@ResponseBody
public class ControllerHader {

    private static final Logger log = LoggerFactory.getLogger(ControllerHader.class);

    /**
     * 处理自定义异常
     * @param req
     * @param e
     * @return
     */
    //类中定义的方法携带@ExceptionHandler注解的会被作为异常处理器，后面添加实际处理的异常类型
    @ExceptionHandler(value = BusinessException.class)
    public ResponseBase bizExceptionHandler(HttpServletRequest req, BusinessException e){
        log.error(">>>>>> 发生业务异常 <<<<<<,cause: ",e.getMessage());
        return ResponseBase.error(e.getCode(),e.getMessage());
    }

    /**
     * 空指针异常并返回错误提示
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = SystemException.class)
    public ResponseBase nullPointExceptionHandler(HttpServletRequest req,SystemException e){
        log.error(">>>>>> 发生空指针异常 <<<<<<,cause: ",e);
        return ResponseBase.error().message(ResponseEnum.NULL_POINT_EXCEPTION.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseBase ExceptionHandler(HttpServletRequest req,Exception e){
        log.error(">>>>>> 发生其他异常 <<<<<<,cause: ",e);
        return ResponseBase.error().message(ResponseEnum.NULL_POINT_EXCEPTION.getMessage());
    }

}
