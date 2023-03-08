package com.hehe.demo3.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2023-02-07 18:32
 * 过滤器获取参数
 */
//过滤器获取参数
@Slf4j
@WebFilter(filterName = "RequestWrapperFilter", urlPatterns = "/*")
public class RequestWrapperFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        log.info("测试============好使============");
//        ServletRequest requestWrapper = null;
//        if (request instanceof HttpServletRequest) {
//            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
//        }
//        if (null == requestWrapper) {
//            log.error("过滤器包装request失败!将返回原来的request");
//            chain.doFilter(request, response);
//        } else {
//            log.info("过滤器包装request成功");
//            chain.doFilter(requestWrapper, response);
//        }

//        String body = HttpHelper.getBodyString(request);
//        Map<String, Object> bodyMap = HttpHelper.getBodyMap(request);
//        System.out.println("body: " + body);
//        System.out.println("bodyMap: " + bodyMap);
//        log.info("测试============好使============");




        //自定义的获取url请求参数的类
        BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
        String body = requestWrapper.getBody();    //获取url请求参数
        log.info("url请求参数{}",body);

        log.info("开始过滤");
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();   //获取方法url
        log.info("url{}",url);

        String method = req.getMethod();   //获取方法的get/post/delete
        log.info("method{}",method);

        Map<String, String[]> map = req.getParameterMap();   //获取get方法的参数
        if(!CollectionUtils.isEmpty(map)){
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                List<String> list = Arrays.asList(entry.getValue());
                for (String str : list) {
                    System.out.println(entry.getKey() + ":" + str);
                }
            }
        }



    }




    /**
     * 向前端返回提示
     */
    public void message(ServletResponse response){
        try {
            PrintWriter writer = response.getWriter();
            //防止前端提示信息乱码
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");

            //print可以输出对象,writer不能,,详情看有道云笔记
            writer.print("给出前端提示");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

}

