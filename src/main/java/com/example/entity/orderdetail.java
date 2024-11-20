package com.example.entity;

import lombok.Data;

//订单详细表
@Data
public class orderdetail {
private Integer id;
private Integer orderbasetable_id;   //订单编号
private Integer goodstable_id;       //商品编号
private Integer shoppingnum;         //购买数量
}
