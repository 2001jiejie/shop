package com.example.service;

import java.util.List;

import com.example.entity.*;

public interface AdminService {
    public aUser login(String ausername, String apassword);

    public List<goodstable> ListAllGoods();

    public List<goodstable> SearchGoods(String gname, Integer goodstype_id);

    public void AddGoods(goodstable good);

    public void UpdateGoods(goodstable good);

    public boolean DeleteGood(Integer id);

    public boolean DeleteGoods(List<Integer> ids);

    public List<goodstype> ListAllGoodstypes();

    public List<goodstype> SearchGoodstype(String typename);

    public void AddGoodstype(goodstype goodstype);

    public boolean DeleteGoodstype(Integer id);

    public boolean DeleteGoodstypes(List<Integer> ids);

    public List<bUser> ListAllUsers();

    public List<bUser> SearchUser(Integer id);

    public boolean DeleteUser(Integer id);

    public boolean DeleteUsers(List<Integer> ids);

    public aUser getUserById(Integer id);

    public List<orderbase> ListAllOrders();

    public List<orderbase> SearchOrder(Integer id);
}