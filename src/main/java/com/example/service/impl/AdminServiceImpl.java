package com.example.service.impl;

import com.example.entity.*;
import com.example.mapper.Adminmapper;
import com.example.mapper.Goodsmapper;
import com.example.service.AdminService;
import com.example.utils.PasswordUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private Adminmapper adminmapper;

    @Autowired
    private Goodsmapper goodsmapper;

    @Override
    public aUser login(String ausername, String apassword) {
        aUser user = adminmapper.findByAdminname(ausername);
        if (user != null && PasswordUtils.matches(apassword, user.getApwd())) {
            return user;
        }
        return null;
    }

    @Override
    public List<goodstable> ListAllGoods() {
        return goodsmapper.selectAll();
    }

    @Override
    public List<goodstable> SearchGoods(String gname, Integer goodstype_id) {
        return adminmapper.searchGoods(gname, goodstype_id);
    }

    public void AddGoods(goodstable good){
        adminmapper.addGoods(good);
    }

    public void UpdateGoods(goodstable good){
        adminmapper.updateGoods(good);
    }

    public boolean DeleteGood(Integer id){
        adminmapper.deleteGood(id);
        if(adminmapper.findGoodById(id)==null){
            return true;
        }else{
            return false;
        }
    }

    public boolean DeleteGoods(List<Integer> ids){
        adminmapper.deleteGoods(ids);
        for(Integer id:ids){
            if(adminmapper.findGoodById(id)!=null){
                return false;
            }
        }
        return true;
    }

    public List<goodstype> ListAllGoodstypes(){
        return adminmapper.getAllGoodstype();
    }

    public List<goodstype> SearchGoodstype(String typename){
        return adminmapper.searchGoodstype(typename);
    }

    public void AddGoodstype(goodstype goodstype){
        adminmapper.addGoodstypes(goodstype);
    }

    public boolean DeleteGoodstype(Integer id){
        adminmapper.deleteGoodstype(id);
        if(adminmapper.findGoodstypeById(id)==null){
            return true;
        }else{
            return false;
        }
    }

    public boolean DeleteGoodstypes(List<Integer> ids){
        adminmapper.deleteGoodstypes(ids);
        for(Integer id:ids){
            if(adminmapper.findGoodstypeById(id)!=null){
                return false;
            }
        }
        return true;
    }

    public List<bUser> ListAllUsers(){
        return adminmapper.getAllUsers();
    }

    public List<bUser> SearchUser(Integer id){
        return adminmapper.searchUser(id);
    }

    public boolean DeleteUser(Integer id){
        adminmapper.deleteUser(id);
        if(adminmapper.findUserById(id)==null){
            return true;
        }else{
            return false;
        }
    }

    public boolean DeleteUsers(List<Integer> ids){
        adminmapper.deleteUsers(ids);
        for(Integer id:ids){
            if(adminmapper.findGoodstypeById(id)!=null){
                return false;
            }
        }
        return true;
    }

    public aUser getUserById(Integer id) {
        return adminmapper.getUserById(id);
    }

    public List<orderbase> ListAllOrders(){
        return goodsmapper.selectAllOrderBase();
    }

    public List<orderbase> SearchOrder(Integer id){
        return adminmapper.searchOrder(id);
    }
}
