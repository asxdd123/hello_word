package com.example.login.mapper;

import com.example.login.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，所以统一配置@MapperScan在扫描路径在application类中
//@Mapper //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可

@Repository
public interface UserMapper {

    User selectUser(@Param("userid") String userId);

}
