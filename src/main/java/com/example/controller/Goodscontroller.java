package com.example.controller;

import com.example.context.BaseContext;
import com.example.entity.Result;
import com.example.entity.focus;
import com.example.entity.goodstable;
import com.example.service.FocusService;
import com.example.service.GoodsService;
import com.example.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Result<List<focus>> GetFocus() {
        Integer userId = BaseContext.getCurrentId();
        if (userId == null) {
            return Result.error("用户未登录");
        }

        List<focus> focusList = focusService.getFocus(userId);
        return Result.success(focusList);
    }
    @PostMapping("/home/focus")
    @ResponseBody
    public Result<String> AddFocus(@RequestBody Map<String, Object> request) {
        List<Integer> goodstableId = (List<Integer>) request.get("goodstableId");
        int userId = BaseContext.getCurrentId();
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
    public Result<String> Purchase(@RequestBody Map<String, Object> request) {
        int userId = BaseContext.getCurrentId();
        List<Integer> goodstableId = (List<Integer>) request.get("goodstableId");
        List<Integer> shoppingnum = (List<Integer>) request.get("shoppingnum");
        orderService.purchase(userId, goodstableId,shoppingnum );
        return Result.success("购买成功");
    }
    @PostMapping("/getGoods")
    @ResponseBody
    public Result<List<goodstable>> AddGoods(@RequestBody Map<String, Object> request) {
        List<Integer> goodstableId = (List<Integer>) request.get("goodstableId");
        return Result.success(goodsService.GetGoodsById(goodstableId));
    }
    @PostMapping("/deleteFocus")
    @ResponseBody
    public Result<String> DeleteFocus(@RequestBody Map<String, Object> request) {
        int userId = BaseContext.getCurrentId();
        List<Integer> goodstableId = (List<Integer>) request.get("goodstableId");
        goodsService.DeleteFocusById(userId, goodstableId);
        return Result.success("删除成功");
    }


}
