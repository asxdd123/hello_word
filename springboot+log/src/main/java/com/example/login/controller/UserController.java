package com.example.login.controller;


import com.example.login.bean.User;
import com.example.login.exception1.ExceptionEnum;
import com.example.login.exception1.NoFoundExcepiton;
import com.example.login.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@EnableAutoConfiguration
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 引入日志，注意都是"org.slf4j"包下
     */
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 根据员工工号，查询员工
     * <p>Title: selectUser</p>
     * @author Liyan
     * @date   2018年1月11日 上午11:37:46
     * @param userId 员工工号
     * @param httpServletResponse
     * @return  User
     * @throws Exception
     */
//    @RequestMapping(value = "selectUser/{userId}",method = RequestMethod.GET)
//    public User selectUser(@PathVariable String userId, HttpServletResponse httpServletResponse) throws Exception {
//        User user = userService.selectUser(userId);
//        logger.info("访问selectUser/{userId}接口，参数userId = " + userId);
//        if (user == null) {
//            logger.error("根据员工工号，查询员工异常：e=" + ExceptionEnum.ERROR_NOFOUND.getValue());
//            throw new NoFoundExcepiton(ExceptionEnum.ERROR_NOFOUND.getValue());
//        }
//        return user;
//    }

}
