package com.example.service.impl;

import com.example.entity.aUser;
import com.example.mapper.Adminmapper;
import com.example.service.AdminService;
import com.example.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private Adminmapper adminmapper;

    @Override
    public aUser login(String ausername, String apassword){
        aUser user = adminmapper.findByAdminname(ausername);
        if(user!=null&& PasswordUtils.matches(apassword,user.getApwd())){
            return user;
        }
        return null;
    }

}
