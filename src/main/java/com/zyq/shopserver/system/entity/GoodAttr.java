package com.zyq.shopserver.system.entity;

import java.io.Serializable;

public class GoodAttr implements Serializable {
    private Integer attr_id;
    private String attr_value;
    private Integer goods_id;
    private Integer add_price;
    private String attr_name;
    private String attr_sel;
    private String attr_write;
    private Integer cat_id;

    public GoodAttr() {
        attr_name = "";
        cat_id = 0;
        attr_sel = "only";
        attr_write = "manual";
        attr_value = "";
    }

    @Override
    public String toString() {
        return "GoodAttr{" +
                "attr_id=" + attr_id +
                ", attr_value='" + attr_value + '\'' +
                ", goods_id=" + goods_id +
                ", add_price=" + add_price +
                ", attr_name='" + attr_name + '\'' +
                ", attr_sel='" + attr_sel + '\'' +
                ", attr_write='" + attr_write + '\'' +
                ", cat_id=" + cat_id +
                '}';
    }

    public Integer getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(Integer attr_id) {
        this.attr_id = attr_id;
    }

    public String getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getAdd_price() {
        return add_price;
    }

    public void setAdd_price(Integer add_price) {
        this.add_price = add_price;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public String getAttr_sel() {
        return attr_sel;
    }

    public void setAttr_sel(String attr_sel) {
        this.attr_sel = attr_sel;
    }

    public String getAttr_write() {
        return attr_write;
    }

    public void setAttr_write(String attr_write) {
        this.attr_write = attr_write;
    }

    public Integer getCat_id() {
        return cat_id;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }
}
