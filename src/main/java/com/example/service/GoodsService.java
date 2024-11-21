package com.example.service;

import com.example.entity.goodstable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GoodsService {
    public List<goodstable> ListAllGoods();

    public List<goodstable> SearchGoods (String gname,String gtype);
}
