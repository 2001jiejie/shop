package com.example.service.impl;

import com.example.entity.aUser;
import com.example.mapper.Adminmapper;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private Adminmapper adminmapper;

    @Override
    public aUser login(String ausername, String apassword){
        return adminmapper.login(ausername,apassword);
    }

}
