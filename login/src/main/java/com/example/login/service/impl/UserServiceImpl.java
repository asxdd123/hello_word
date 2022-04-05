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
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public boolean login(User user) {
        String name = user.getName();
        String password = user.getPassword();
        User u1 = this.selectUserByName(name);
        if (u1 == null) {
            return false;
        } else {
            if (u1.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean zhuCe(User user) {
        int x = this.insertUser(user);
        if (x > 0) {
            return true;
        } else {
            return false;
        }
    }

}
