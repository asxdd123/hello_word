package com.helllo.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helllo.demo.mapper.LoginMapper;
import com.helllo.demo.pojo.Login;
import com.helllo.demo.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements LoginService {
}
