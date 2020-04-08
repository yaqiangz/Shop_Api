package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.GoodAttr;
import com.zyq.shopserver.system.entity.GoodPic;
import com.zyq.shopserver.system.entity.Goods;
import com.zyq.shopserver.system.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImp implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> getGoodsList(String query) {
        return goodsMapper.getGoodsList(query);
    }

    @Override
    public Goods getGoodsByName(String query) {
        return goodsMapper.getGoodsByName(query);
    }

    @Override
    public Integer insertGoods(Goods goods) {
        return goodsMapper.insertGoods(goods);
    }

    @Override
    public Integer insertPics(GoodPic goodPic, Integer goodsId) {
        return goodsMapper.insertPics(goodPic, goodsId);
    }

    @Override
    public Integer insertAttr(GoodAttr goodAttr) {
        return goodsMapper.insertAttr(goodAttr);
    }

    @Override
    public Integer insertGoodAttr(GoodAttr goodAttr, Integer goodId) {
        return goodsMapper.insertGoodAttr(goodAttr, goodId);
    }
}
