package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.GoodAttr;
import com.zyq.shopserver.system.entity.GoodPic;
import com.zyq.shopserver.system.entity.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> getGoodsList(String query);
    Goods getGoodsByName(String query);
    Integer insertGoods(Goods goods);
    Integer insertPics(GoodPic goodPic, Integer goodsId);
    Integer insertAttr(GoodAttr goodAttr);
    Integer insertGoodAttr(GoodAttr goodAttr, Integer goodId);
}
