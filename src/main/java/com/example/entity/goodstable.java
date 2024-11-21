package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//商品信息表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class goodstable {
private Integer id;
private String gname;
private float goprice;       //原价
private float grprice;       //现价
private Integer gstore;      //库存
private String gpicture;     //图片
private Integer goodstype_id;             //商品类型id
}
