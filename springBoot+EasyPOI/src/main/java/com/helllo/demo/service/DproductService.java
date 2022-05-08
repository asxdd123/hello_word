package com.helllo.demo.service;

import com.helllo.demo.pojo.Dproduct;

import java.util.List;

public interface DproductService {
    /**
     *
     * 产品图片一对多嵌套
     *
     */
    public List<Dproduct> productSelect();
}
