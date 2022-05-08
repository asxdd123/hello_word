package com.helllo.demo.service.impl;

import com.helllo.demo.mapper.DproductMapper;
import com.helllo.demo.pojo.Dproduct;
import com.helllo.demo.service.DproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DproductServiceImpl implements DproductService {
    @Autowired
    private DproductMapper mapper;

    @Override
    public List<Dproduct> productSelect() {
        return mapper.productSelect();
    }
}
