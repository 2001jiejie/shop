package com.example.service;

import java.util.List;

import com.example.entity.focus;

public interface FocusService {
    public void addFocus(int userId, List<Integer> goodsIdList );
    public void deleteFocus(int userId, int goodstableId);
    public List<focus> getFocus(int userId);
}
