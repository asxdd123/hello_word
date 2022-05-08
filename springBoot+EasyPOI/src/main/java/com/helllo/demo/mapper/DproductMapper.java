package com.helllo.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.helllo.demo.pojo.Dproduct;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DproductMapper extends BaseMapper<Dproduct> {
    /**
     *
     * 产品图片一对多嵌套
     *
     *
     */
    public List<Dproduct> productSelect();
}
