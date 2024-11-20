package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//商品类型表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class goodstype {
    private Integer id;
    private String typename;    //类型名称
}
