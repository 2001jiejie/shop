package com.example.service;

import com.example.entity.bUser;
import com.example.entity.cart;

public interface UserService {
    //用户登录
    public bUser login(String username, String password);

    public boolean register(String username, String password);

    public bUser getUserById(Integer id);

    public void addCart(Integer buserId,Integer goodstable_id);
}
