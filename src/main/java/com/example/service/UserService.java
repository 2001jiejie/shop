package com.example.service;

import com.example.entity.bUser;

public interface UserService {
    //用户登录
    public bUser login(String username, String password);
}
