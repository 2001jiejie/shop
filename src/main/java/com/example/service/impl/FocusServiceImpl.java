package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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
    public void addFocus(int userId, List<Integer>goodsIdList) {
        List<Integer> existingFocus = goodsmapper.selectExistingFocus(userId);

        // 2. 过滤掉已经收藏的商品ID
        List<Integer> newGoodsIds = goodsIdList.stream()
                .filter(goodsId -> !existingFocus.contains(goodsId))
                .collect(Collectors.toList());

        // 3. 如果有新的商品ID需要添加，则执行批量插入
        if (!newGoodsIds.isEmpty()) {
            goodsmapper.insertFocusBatch(userId, newGoodsIds);
        }
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
