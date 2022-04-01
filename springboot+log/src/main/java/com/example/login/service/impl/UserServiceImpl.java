package com.example.login.service.impl;

import com.example.login.bean.User;
import com.example.login.mapper.UserMapper;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUser(String userId) {
        return userMapper.selectUser(userId);
    }

}
