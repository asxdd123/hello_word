package com.example.login.service;

import com.example.login.bean.User;

public interface UserService {
    User selectUserById(int id);

    User selectUserByName(String name);

    int insertUser(User user);

    boolean login(User user);

    boolean zhuCe(User user);

}
