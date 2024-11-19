package com.example.shop.entity;

import lombok.Data;

import java.util.Date;

@Data
public class orderbase {
   private Integer id;
   private Integer bustable_id;
   private Integer amount;
   private String status;
   private Date orderdate;
}
