package com.zyq.shopserver.system.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class OrderGoods {
    @JSONField(name = "id", ordinal = 1)
    Integer id;
    @JSONField(name = "order_id", ordinal = 2)
    Integer orderId;
    @JSONField(name = "goods_id", ordinal = 3)
    Integer goodsId;
    @JSONField(name = "goods_price", ordinal = 4)
    Double goodsPrice;
    @JSONField(name = "goods_number", ordinal = 5)
    Integer goodsNumber;
    @JSONField(name = "goods_total_price", ordinal = 6)
    Integer goodsTotalPrice;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Integer getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(Integer goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderGoods{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", goodsPrice=" + goodsPrice +
                ", goodsNumber=" + goodsNumber +
                ", goodsTotalPrice=" + goodsTotalPrice +
                '}';
    }
}
