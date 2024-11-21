package com.example.service.impl;

import com.example.entity.goodstable;
import com.example.mapper.GoodsTypemapper;
import com.example.mapper.Goodsmapper;
import com.example.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private Goodsmapper goodsmapper;
    public List<goodstable> ListAllGoods(){
        return goodsmapper.selectAll();
    }
    @Override
    public List<goodstable> SearchGoods(String gname,String gtype){
        Integer goodsTypeId=null;
        if(gtype != null && !gtype.isEmpty()){
            goodsTypeId=GoodsTypemapper.getTypeId(gtype);
        }
        return goodsmapper.searchGoods(gname,goodsTypeId);
    }
}
