package com.example.controller;

import com.example.entity.Result;
import com.example.entity.focus;
import com.example.entity.goodstable;
import com.example.service.FocusService;
import com.example.service.GoodsService;
import com.example.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Goodscontroller {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private FocusService focusService;
    @Autowired
    private OrderService orderService;
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
            , @RequestParam(value = "gtype", required = false) String gtype) {
        List<goodstable> goodsList = goodsService.SearchGoods(gname, gtype);
        if (goodsList != null && !goodsList.isEmpty()) {
            return Result.success(goodsList);
        } else {
            return Result.error("没有查询到结果");
        }
    }
    @GetMapping("/home/focus")
    @ResponseBody
    public Result<List<focus>> GetFocus(@RequestParam("userId") int userId) {
        List<focus> focusList = focusService.getFocus(userId);
        return Result.success(focusList);
    }
    @PostMapping("/home/focus")
    @ResponseBody
    public Result<String> AddFocus(@RequestParam("userId") int userId, @RequestParam("goodstableId") int goodstableId) {
        focusService.addFocus(userId, goodstableId);
        return Result.success("收藏成功");
    }
    @DeleteMapping("/home/focus")
    @ResponseBody
    public Result<String> DeleteFocus(@RequestParam("userId") int userId,
            @RequestParam("goodstableId") int goodstableId) {
        focusService.deleteFocus(userId, goodstableId);
        return Result.success("取消收藏成功");
    }
    @PostMapping("/home/purchase")
    @ResponseBody
    public Result<String> Purchase(@RequestParam("userId") int userId,
            @RequestParam("goodstableId") int goodstableId, @RequestParam("shoppingnum") int shoppingnum) {
        orderService.purchase(userId, goodstableId, shoppingnum);
        return Result.success("购买成功");
    }
}
