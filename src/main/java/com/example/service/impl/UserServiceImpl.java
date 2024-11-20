package com.example.service.impl;

import com.example.entity.bUser;
import com.example.mapper.Usermapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Usermapper usermapper;

    //用户登录
    @Override
    public bUser login(String username, String password){
        return usermapper.login(username,password);
    }
}
