package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//商品收藏表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class focus {
    private Integer id;
    private Integer bustable_id;        //用户id
    private Integer goodstable_id;      //商品编号
}

