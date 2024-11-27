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

    public void purchase(int userId, List<Integer> goodstableId, List<Integer> shoppingnum) {
        double totalAmount = 0;

        // 先计算总金额
        for (int i = 0; i < goodstableId.size(); i++) {
            int goodsId = goodstableId.get(i);
            int shoppingNum = shoppingnum.get(i);
            double price = goodsmapper.getPriceByGoodstableId(goodsId);
            totalAmount += price * shoppingNum;
        }
        totalAmount = Math.round(totalAmount * 100.0) / 100.0; // 保留两位小数

        // 先创建订单基础信息
        orderbase order = new orderbase();
        order.setBustable_id(userId);
        order.setAmount(totalAmount);
        order.setStatus(2);
        order.setOrderdate(new Date(System.currentTimeMillis()));

        goodsmapper.insertOrderBase(order);
        int orderId = order.getId();  // 获取订单ID

        // 再处理订单详情
        for (int i = 0; i < goodstableId.size(); i++) {
            int goodsId = goodstableId.get(i);
            int shoppingNum = shoppingnum.get(i);

            // 检查并更新库存
            int gstore = goodsmapper.getStoreByGoodstableId(goodsId);
            if (gstore < shoppingNum) {
                throw new RuntimeException("库存不足");
            } else {
                goodsmapper.updateStoreByGoodstableId(goodsId, gstore- shoppingNum);
            }

            // 创建订单详情
            orderdetail detail = new orderdetail();
            detail.setOrderbasetable_id(orderId);  // 现在可以使用orderId了
            detail.setGoodstable_id(goodsId);
            detail.setShoppingnum(shoppingNum);

            List<orderdetail> orderDetails = new ArrayList<>();
            orderDetails.add(detail);
            goodsmapper.insertOrderDetail(orderDetails);
        }
    }

    public List<orderdetail> getOrderdeatil(int orderbase_id){
       return goodsmapper.selectOrderDetail(orderbase_id);
    }

}