package com.heim.longindenglu.mapper;

import com.heim.longindenglu.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-07-13 23:39
 */
@Repository
@Mapper
public interface UserMapper {
    User result(String username);

    int selectCount(String username);

    int insetUser(@Param("username") String username, @Param("password") String password);

}
