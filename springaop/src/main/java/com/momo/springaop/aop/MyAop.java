package com.momo.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component   //让springboot扫描到这和个类
@Aspect        // 告诉springboot这是个切面
public class MyAop {

    //切入点：待增强的方法
    @Pointcut("execution(public * com.momo.springaop.controller.*.*(..)) ")
    public void log() {
    }


    //前置通知
    @Before(value = "log()")
    public void deBefore(JoinPoint jp) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("前置通知开始执行");
        System.out.println(jp.toString());
        System.out.println("====================");
        System.out.println("URL : " + request.getRequestURL().toString());   // URL : http://localhost:8080/helloAop
        System.out.println("HTTP_METHOD : " + request.getMethod());  // HTTP_METHOD : GET
        System.out.println("CLASS_METHOD : " + jp);   // CLASS_METHOD : execution(Object com.momo.springaop.controller.HelloController.hello())
        System.out.println("ARGS : " + Arrays.toString(jp.getArgs()));  // ARGS : [] []里是url里携带的参数

        System.out.println("getThis: " + jp.getThis());  //切入点所在的controller
        System.out.println("getName: "+jp.getSignature().getName());  //获取方法名
    }


    //后置通知
    @After("log()")
    public void after(JoinPoint jp) {
        System.out.println("后置通知：最后且一定执行.....");
    }


    //返回通知
    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("返回通知：方法的返回值 : " + ret);
    }

    //异常通知
    @AfterThrowing(throwing = "ex", pointcut = "log()")
    public void throwss(JoinPoint jp,Exception ex){
        System.out.println("异常通知：方法异常时执行.....");
        System.out.println("产生异常的方法："+jp);
        System.out.println("异常种类："+ex);
    }


}

