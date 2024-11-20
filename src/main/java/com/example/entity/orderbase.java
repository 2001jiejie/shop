package com.example.entity;

import lombok.Data;

import java.util.Date;

//订单基础表
@Data
public class orderbase {
   private Integer id;
   private Integer bustable_id;      //用户id
   private Integer amount;           //订单金额
   private String status;            //订单状态
   private Date orderdate;           //下单时间
}
