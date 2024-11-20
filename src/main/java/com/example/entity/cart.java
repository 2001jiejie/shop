package com.example.entity;

import lombok.Data;

//购物车表
@Data
public class cart {
   private Integer id;
   private  Integer bustable_id;    //用户id
   private Integer goodstable_id;   //商品id
   private Integer shoppingnum;     //购买数量
}