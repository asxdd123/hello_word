package com.hehe.demo3.config;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: 就不告诉你
 * 登录拦截器
 * @CreateTime: 2022-11-07 11:07
 */
@Configuration
public class UserLoginInterceptor implements HandlerInterceptor {


    /**
     * 在请求处理之前进行调用(Controller方法调用之前)
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    // //就是通过这个拦截器，使得 Controller 在执行之前，都执行一遍preHandle.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("已进入拦截方法");
        // 检查 session 中是否有user对象存在；
        HttpSession session = request.getSession();
        Object user = session.getAttribute("sessionid");
        //如果存在，就返回true，那么 Controller 就会继续后面的操作
        if (ObjectUtils.isEmpty(user)) {
            //如果不存在，就会重定向到登录界面
            response.sendRedirect("login");
            return false;
        }else{
            return true;
        }
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("已进入postHandle拦截方法");
    }


    /***
     * 整个请求结束之后被调用，也就是在DispatchServlet渲染了对应的视图之后执行（主要用于进行资源清理工作）
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("已进入afterCompletion拦截方法");
    }
}
