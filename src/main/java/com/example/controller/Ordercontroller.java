package com.example.controller;

import com.example.entity.cart;
import com.example.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class Ordercontroller {
    @Autowired
    private OrderService orderService;

    @PostMapping("/home/order")
    @ResponseBody
    public String Setorder(@RequestBody List<cart> cartList) {
        if (cartList == null || cartList.isEmpty()) {
            return "购物车为空，无法创建订单";
        }
        int userId = cartList.get(0).getBustable_id(); // 从购物车对象中获取用户ID
        orderService.placeOrder(userId, cartList);
        return "订单已成功创建";
    }
}
