package com.example.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.service.OrderService;
import org.springframework.stereotype.Service;

import com.example.entity.cart;
import com.example.entity.orderdetail;
import com.example.mapper.Goodsmapper;

@Service
public class OrderServiceImpl implements OrderService {
    private final Goodsmapper goodsmapper;

    public OrderServiceImpl(Goodsmapper goodsmapper) {
        this.goodsmapper = goodsmapper;
    }

    public void placeOrder(int userId, List<cart> cart) {

        double totalAmount = 0;
        for (cart item : cart) {
            double price = goodsmapper.getPriceByGoodstableId(item.getGoodstable_id());

            System.out.println(price);
            System.out.println(item.getShoppingnum());
            totalAmount += price * item.getShoppingnum();
        }
        totalAmount = Math.round(totalAmount * 100.0) / 100.0; // 保留两位小数
        
        
        // 2. 插入订单基础信息
        int orderId = goodsmapper.insertOrderBase(userId, totalAmount, 1, new Date(System.currentTimeMillis()));



        // 4. 插入订单详情信息
        List<orderdetail> orderDetails = new ArrayList<>();
        for (cart item : cart) {
            orderdetail detail = new orderdetail();
            detail.setOrderbasetable_id(orderId);
            detail.setGoodstable_id(item.getGoodstable_id());
            detail.setShoppingnum(item.getShoppingnum());
            orderDetails.add(detail);
        }
        goodsmapper.insertOrderDetail(orderDetails);
    }
}