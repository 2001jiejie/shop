package com.example.service;

import java.util.List;

import com.example.entity.bUser;
import com.example.entity.cart;

public interface UserService {
    // 用户登录
    public bUser login(String username, String password);

    public boolean register(String username, String password);

    public bUser getUserById(Integer id);

    public void addCart(Integer buserId, Integer goodstable_id);

    public List<cart> getCart(Integer buserId);

    public boolean deleteCart(Integer buserId, Integer goodstable_id);
}
