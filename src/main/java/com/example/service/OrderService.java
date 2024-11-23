package com.example.service;

import java.util.List;

import com.example.entity.cart;
import com.example.entity.orderbase;

public interface OrderService {
    public void placeOrder(int userId, List<cart> cart);

    public List<orderbase> getOrder(int userId);
    
    public void purchase(int userId, int goodstableId, int shoppingnum);
}
