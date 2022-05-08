package com.helllo.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helllo.demo.mapper.RegionEntityMapper;
import com.helllo.demo.pojo.RegionEntity;
import com.helllo.demo.service.RegionEntityService;
import org.springframework.stereotype.Service;

@Service
public class RegionEntityServiceImpl extends ServiceImpl<RegionEntityMapper, RegionEntity> implements RegionEntityService {
}
