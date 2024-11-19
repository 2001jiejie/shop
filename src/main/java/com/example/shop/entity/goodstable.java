package com.example.shop.entity;

import lombok.Data;

@Data
public class goodstable {
private Integer id;
private String gname;
private float goprice;
private float grprice;
private Integer gstore;
private String gpicture;
private boolean isRecommend;
private boolean isAdvertisement;
private Integer goodstype_id;
}
