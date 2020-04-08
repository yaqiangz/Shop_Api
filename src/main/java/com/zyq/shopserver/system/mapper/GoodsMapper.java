package com.zyq.shopserver.system.mapper;

import com.zyq.shopserver.system.entity.GoodAttr;
import com.zyq.shopserver.system.entity.GoodPic;
import com.zyq.shopserver.system.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    List<Goods> getGoodsList(@Param("query") String query);
    Goods getGoodsByName(@Param("goods_name") String query);
    Integer insertGoods(@Param("goods") Goods goods);
    Integer insertPics(@Param("pic") GoodPic goodPic, @Param("goods_id") Integer goodId);
    Integer insertAttr(@Param("attr") GoodAttr goodAttr);
    Integer insertGoodAttr(@Param("attr") GoodAttr goodAttr, @Param("goods_id") Integer goodsId);
}
