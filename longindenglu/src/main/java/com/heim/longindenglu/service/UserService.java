package com.heim.longindenglu.service;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-07-13 23:38
 */
public interface UserService {
    boolean result(String username, String password);

    int selectCount(String username);

    int insetUser(String username, String password);
}
