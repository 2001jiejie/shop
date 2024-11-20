package com.example.service.impl;

import com.example.entity.bUser;
import com.example.mapper.Usermapper;
import com.example.service.UserService;
import com.example.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Usermapper usermapper;

    //用户登录
    @Override
    public bUser login(String username, String password){
        bUser user=usermapper.findByUsername(username);
        if(user!=null&&PasswordUtils.matches(password,user.getBpwd())){
            return user;
        }
        return null;
    }

    @Override
    public boolean register(String username, String password) {
        if(usermapper.checkUsername(username)==0){

            String encryptedPassword= PasswordUtils.encryptPassword(password);
            usermapper.register(username,encryptedPassword);
            return true;
        }

        return false;
    }
}
