package com.example.login.service;

import com.example.login.bean.User;

public interface UserService {
    /**
     * 根据员工工号，查询员工
     * <p>Title: selectUser</p>
     * @author Liyan
     * @date   2018年1月11日 上午10:50:34
     * @param userId 员工工号
     * @return  List<User>
     */
     User selectUser(String userId);

}
