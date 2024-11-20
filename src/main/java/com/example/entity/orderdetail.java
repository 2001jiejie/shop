package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//订单详细表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class orderdetail {
private Integer id;
private Integer orderbasetable_id;   //订单编号
private Integer goodstable_id;       //商品编号
private Integer shoppingnum;         //购买数量
}
