package com.example.controller;

import com.example.context.BaseContext;
import com.example.entity.Result;
import com.example.entity.bUser;
import com.example.entity.cart;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Usercontroller {
    @Autowired
    private UserService userservice;

    // 用户登录
    @PostMapping("/login")
    public Result<String> Login(@RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request) {
        bUser user = userservice.login(username, password);
        if (user != null) {
            String jwt = JwtUtils.generateToken(user.getId());
            return Result.success(jwt);
        } else {
            return Result.error("用户名或密码错误");
        }
    }

    // 用户注册
    @PostMapping("/register")
    public Result<String> Register(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        if (userservice.register(username, password)) {
            return Result.success();
        }
        return Result.error("注册失败");
    }

    // 用户个人信息
    @GetMapping("/user")
    public Result<bUser> getUser() {
        Integer buserId = BaseContext.getCurrentId();
        bUser buser = userservice.getUserById(buserId);
        if (buser != null) {
            return Result.success(buser);
        } else {
            return Result.error("用户不存在");
        }
    }

    // 用户退出
    @GetMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    // 添加商品到购物车
    @PostMapping("/addcart")
    public Result<String> addCart(@RequestBody Map<String, Object> request) {
        Integer buserId = BaseContext.getCurrentId();
        List<Integer> goodstableId = (List<Integer>) request.get("goodstableId");
        List<Integer> shoppingNum = (List<Integer>) request.get("shoppingnum");
        userservice.addCart(buserId, goodstableId,shoppingNum);
        return Result.success();
    }

    // 查看用户的购物车
    @GetMapping("/cart")
    public Result<List<cart>> getCart() {
        Integer buserId = BaseContext.getCurrentId();
        List<cart> cart = userservice.getCart(buserId);
        return Result.success(cart);
    }

    // 删除购物车
    @DeleteMapping("/deletecart")
    public Result<String> deleteCart(@RequestParam("goodstable_id") Integer goodstable_id) {
        Integer buserId = BaseContext.getCurrentId();
        if (userservice.deleteCart(buserId, goodstable_id)) {
            return Result.success();
        }
        return Result.error("删除失败");
    }

}
