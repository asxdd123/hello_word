package com.hehe.demo3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-11-07 11:15
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    /**
     * 将拦截器注册到了拦截器列表中，并且指明了拦截哪些访问路径，不拦截哪些访问路径，不拦截哪些资源文件；最后再以 @Configuration 注解将配置注入。
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new UserLoginInterceptor());
        registration.addPathPatterns("/**"); //所有路径都被拦截
        registration.excludePathPatterns(    //添加不拦截路径
                "/aa/login",                    //登录页面路径
                "/aa/result",                   //登录接口
                "/aa/end",                   //注册接口
                "/aa/end1",                   //注册接口
                "/**/*.html",                //html静态资源
                "/**/*.js",                  //js静态资源
                "/**/*.css"                  //css静态资源
        );
    }

}
