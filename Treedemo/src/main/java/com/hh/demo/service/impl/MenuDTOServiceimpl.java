package com.hh.demo.service.impl;

import com.hh.demo.TreeBean.MenuDTO;
import com.hh.demo.mapper.MenuDTOMapper;
import com.hh.demo.service.MenuDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuDTOServiceimpl implements MenuDTOService {

    @Autowired
    private MenuDTOMapper mapper;

    @Override
    public List<MenuDTO> menuList() {
        return mapper.menuList();
    }
}
