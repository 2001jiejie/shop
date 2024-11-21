package com.example.controller;

import com.example.entity.Result;
import com.example.entity.goodstable;
import com.example.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Goodscontroller {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/home")
    @ResponseBody
    public Result<List<goodstable>> GetAllGoods() {
        List<goodstable> goodsList = goodsService.ListAllGoods();
        if (goodsList != null && !goodsList.isEmpty()) {
            return Result.success(goodsList);
        } else {
            return Result.error("没找到商品");
        }
    }

    @GetMapping("/home/search")
    @ResponseBody
    public Result<List<goodstable>> SearchGoods(@RequestParam (value="gname",required=false) String gname
    ,@RequestParam(value="gtype",required = false) String gtype) {
        List<goodstable> goodsList=goodsService.SearchGoods(gname,gtype);
        if (goodsList != null && !goodsList.isEmpty()) {
            return Result.success(goodsList);
        }else {
            return Result.error("没有查询到结果");
        }
    }
}
