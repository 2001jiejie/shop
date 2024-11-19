package com.example.shop.entity;

import lombok.Data;

@Data
public class orderdetail {
private Integer id;
private Integer orderbasetable_id;
private Integer goodstable_id;
private Integer shoppingnum;
}
