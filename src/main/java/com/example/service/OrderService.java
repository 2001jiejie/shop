package com.example.service;

import java.util.List;

import com.example.entity.cart;

public interface OrderService {
    public void placeOrder(int userId, List<cart> cart);
}
