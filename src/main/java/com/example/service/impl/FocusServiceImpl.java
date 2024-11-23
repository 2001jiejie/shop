package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.focus;
import com.example.mapper.Goodsmapper;
import com.example.service.FocusService;

@Service
public class FocusServiceImpl implements FocusService {
    @Autowired
    private Goodsmapper goodsmapper;

    @Override
    public void addFocus(int userId, int goodstableId) {
        goodsmapper.insertFocus(userId, goodstableId);
    }

    @Override
    public void deleteFocus(int userId, int goodstableId) {
        goodsmapper.deleteFocus(userId, goodstableId);
    }

    @Override
    public List<focus> getFocus(int userId) {
        return goodsmapper.selectFocusByBustableId(userId);
    }
}
