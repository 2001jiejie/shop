package com.example.service.impl;

import com.example.entity.bUser;
import com.example.entity.cart;
import com.example.mapper.Usermapper;
import com.example.service.UserService;
import com.example.utils.PasswordUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Usermapper usermapper;

    // 用户登录
    @Override
    public bUser login(String username, String password) {
        bUser user = usermapper.findByUsername(username);
        if (user != null && PasswordUtils.matches(password, user.getBpwd())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(String username, String password) {
        if (usermapper.checkUsername(username) == 0) {

            String encryptedPassword = PasswordUtils.encryptPassword(password);
            usermapper.register(username, encryptedPassword);
            return true;
        }

        return false;
    }

    @Override
    public bUser getUserById(Integer id) {
        return usermapper.getUserById(id);
    }

    @Override
    public void addCart(Integer buserId, Integer goodstable_id) {
        Integer checkCartExists = usermapper.checkCartExists(buserId, goodstable_id);
        if (checkCartExists == 0) {
            usermapper.addCart(buserId, goodstable_id, 1);
        } else {
            Integer shoppingNum = usermapper.getShoppingNum(buserId, goodstable_id);
            shoppingNum++;
            usermapper.updateCart(buserId, goodstable_id, shoppingNum);
        }
    }

    @Override
    public List<cart> getCart(Integer buserId) {
        return usermapper.getCart(buserId);
    }

    @Override
    public boolean deleteCart(Integer buserId, Integer goodstable_id) {
        usermapper.deleteCart(buserId, goodstable_id);
        if (usermapper.checkCartExists(buserId, goodstable_id) == 0) {
            return true;
        }
        return false;
    }
}
