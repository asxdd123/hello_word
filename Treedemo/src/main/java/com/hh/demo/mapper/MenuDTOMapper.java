package com.hh.demo.mapper;

import com.hh.demo.TreeBean.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuDTOMapper {

    @Select("select * from sys_dept")
    List<MenuDTO> menuList();
}
