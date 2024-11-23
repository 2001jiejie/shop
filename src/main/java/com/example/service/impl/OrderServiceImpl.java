package com.example.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.service.OrderService;
import org.springframework.stereotype.Service;

import com.example.entity.cart;
import com.example.entity.orderbase;
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

            int gstore = goodsmapper.getStoreByGoodstableId(item.getGoodstable_id());
            if (gstore < item.getShoppingnum()) {
                throw new RuntimeException("库存不足");
            } else {
                goodsmapper.updateStoreByGoodstableId(item.getGoodstable_id(), gstore - item.getShoppingnum());
            }
            double price = goodsmapper.getPriceByGoodstableId(item.getGoodstable_id());
            totalAmount += price * item.getShoppingnum();
        }
        totalAmount = Math.round(totalAmount * 100.0) / 100.0; // 保留两位小数

        // 2. 插入订单基础信息
        orderbase order = new orderbase();
        order.setBustable_id(userId);
        order.setAmount(totalAmount);
        order.setStatus(1);
        order.setOrderdate(new Date(System.currentTimeMillis()));
        goodsmapper.insertOrderBase(order);
        int orderId = order.getId();
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
    
    public List<orderbase> getOrder(int userId) {
        return goodsmapper.selectOrderBaseByBustableId(userId);
    }

    public void purchase(int userId, int goodstableId, int shoppingnum) {
        int gstore = goodsmapper.getStoreByGoodstableId(goodstableId);
        if (gstore < shoppingnum) {
            throw new RuntimeException("库存不足");
        } else {
            goodsmapper.updateStoreByGoodstableId(goodstableId, gstore - shoppingnum);
        }
        double price = goodsmapper.getPriceByGoodstableId(goodstableId);
        double totalAmount = price * shoppingnum;
        totalAmount = Math.round(totalAmount * 100.0) / 100.0; // 保留两位小数

        orderbase order = new orderbase();
        order.setBustable_id(userId);
        order.setAmount(totalAmount);
        order.setStatus(2);
        order.setOrderdate(new Date(System.currentTimeMillis()));
        
        goodsmapper.insertOrderBase(order);
        int orderId = order.getId();

        orderdetail detail = new orderdetail();
        detail.setOrderbasetable_id(orderId);
        detail.setGoodstable_id(goodstableId);
        detail.setShoppingnum(shoppingnum);
        List<orderdetail> orderDetails = new ArrayList<>();
        orderDetails.add(detail);
        goodsmapper.insertOrderDetail(orderDetails);
    }
}