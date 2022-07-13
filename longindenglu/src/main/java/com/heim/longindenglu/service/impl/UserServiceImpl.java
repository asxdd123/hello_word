package com.heim.longindenglu.service.impl;

import com.heim.longindenglu.bean.User;
import com.heim.longindenglu.mapper.UserMapper;
import com.heim.longindenglu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-07-13 23:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean result(String username, String password) {
       User user =  userMapper.result(username);
       if(password.equals(user.getPassword())){
           return true;
       }
       return false;
    }

    @Override
    public int selectCount(String username) {
        return userMapper.selectCount(username);
    }

    @Override
    public int insetUser(String username, String password) {
        return userMapper.insetUser(username,password);
    }
}
