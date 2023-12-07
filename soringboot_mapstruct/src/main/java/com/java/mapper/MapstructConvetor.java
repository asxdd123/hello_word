package com.java.mapper;

import com.java.entity.UserDTO;
import com.java.pojo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @Author 一条不会飞的咸鱼
 * @Date 2023/12/6 22:18
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface MapstructConvetor {
    @Mappings({
            @Mapping(source = "name",target = "realName"),
            @Mapping(source = "age",target = "realAge"),
            @Mapping(source = "birthday",target = "realBirthday",dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "phone",target = "photo")
    })
    UserDTO toProgramerDto(UserVO userVO);
}
