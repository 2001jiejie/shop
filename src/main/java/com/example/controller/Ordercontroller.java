package com.example.controller;

import com.example.context.BaseContext;
import com.example.entity.Result;
import com.example.entity.cart;
import com.example.entity.orderbase;
import com.example.entity.orderdetail;
import com.example.service.OrderService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getOrderbase")
    @ResponseBody
    public Result<List<orderbase>> Getoderbase() {
        int userId = BaseContext.getCurrentId();
        List<orderbase> orderList = orderService.getOrder(userId);
        return Result.success(orderList);
    }

    @GetMapping("/getOrderDetail/{id}")
    @ResponseBody
    public Result<List<orderdetail>> getOrderDetail(@PathVariable Integer id) {
        try {
            // 参数校验
            if (id == null || id <= 0) {
                return Result.error("订单ID不合法");
            }

            // 调用服务层方法获取订单详情
            List<orderdetail> orderDetail = orderService.getOrderdeatil(id);

            // 判断查询结果
            if (orderDetail == null) {
                return Result.error("未找到ID为 " + id + " 的订单信息");
            }

            return Result.success(orderDetail);

        } catch (Exception e) {
            return Result.error("获取订单详情失败：" + e.getMessage());
        }
    }

}