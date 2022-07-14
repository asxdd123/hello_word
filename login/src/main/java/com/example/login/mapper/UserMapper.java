package com.example.login.mapper;

import com.example.login.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    public User selectUserById(int id);
    public User selectUserByName(String name);
    public int insertUser(User user);

}
