package com.hh.demo.mapper;

import com.hh.demo.TreeBean.DeptEmtity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<DeptEmtity> getNodeTree();
}
