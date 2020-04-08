package com.zyq.shopserver.system.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class Goods implements Serializable {
    private Integer goods_id;
    private String goods_name;
    private Integer goods_price;
    private Integer goods_number;
    private Integer goods_weight;
    private Integer goods_state;
    private Integer add_time;
    private Integer upd_time;
    private Integer hot_mumber;
    private Integer is_promote;

    @JSONField(serialize = false)
    private String goods_introduce;
    @JSONField(serialize = false)
    private List<GoodPic> pics;
    @JSONField(serialize = false)
    private List<GoodAttr> attrs;
    @JSONField(serialize = false)
    private String goods_cat;
    @JSONField(serialize = false)
    private String goods_big_logo;
    @JSONField(serialize = false)
    private String goods_small_logo;
    @JSONField(serialize = false)
    private Integer cat_one_id;
    @JSONField(serialize = false)
    private Integer cat_two_id;
    @JSONField(serialize = false)
    private Integer cat_three_id;
    @JSONField(serialize = false)
    private String is_del;
    @JSONField(serialize = false)
    private Integer delete_time;

    public Goods() {
        goods_name = "";
        goods_price = 0;
        goods_number = 0;
        goods_weight = 0;
        goods_state = 0;
        hot_mumber = 0;
        is_promote = 0;
        goods_introduce = "";
        goods_big_logo = "";
        goods_small_logo = "";
        cat_one_id = 0;
        cat_two_id = 0;
        cat_three_id = 0;
        is_del = "0";
        delete_time = 0;
    }

    public Integer getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Integer delete_time) {
        this.delete_time = delete_time;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goods_id=" + goods_id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_price=" + goods_price +
                ", goods_number=" + goods_number +
                ", goods_weight=" + goods_weight +
                ", goods_state=" + goods_state +
                ", add_time=" + add_time +
                ", upd_time=" + upd_time +
                ", hot_mumber=" + hot_mumber +
                ", is_promote=" + is_promote +
                ", goods_introduce='" + goods_introduce + '\'' +
                ", pics=" + pics +
                ", attrs=" + attrs +
                ", goods_cat='" + goods_cat + '\'' +
                ", goods_big_logo='" + goods_big_logo + '\'' +
                ", goods_small_logo='" + goods_small_logo + '\'' +
                ", cat_one_id=" + cat_one_id +
                ", cat_two_id=" + cat_two_id +
                ", cat_three_id=" + cat_three_id +
                ", is_del=" + is_del +
                ", delete_time=" + delete_time +
                '}';
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public Integer getCat_one_id() {
        return cat_one_id;
    }

    public void setCat_one_id(Integer cat_one_id) {
        this.cat_one_id = cat_one_id;
    }

    public Integer getCat_two_id() {
        return cat_two_id;
    }

    public void setCat_two_id(Integer cat_two_id) {
        this.cat_two_id = cat_two_id;
    }

    public Integer getCat_three_id() {
        return cat_three_id;
    }

    public void setCat_three_id(Integer cat_three_id) {
        this.cat_three_id = cat_three_id;
    }

    public String getGoods_cat() {
        return goods_cat;
    }

    public void setGoods_cat(String goods_cat) {
        this.goods_cat = goods_cat;
    }

    public String getGoods_big_logo() {
        return goods_big_logo;
    }

    public void setGoods_big_logo(String goods_big_logo) {
        this.goods_big_logo = goods_big_logo;
    }

    public String getGoods_small_logo() {
        return goods_small_logo;
    }

    public void setGoods_small_logo(String goods_small_logo) {
        this.goods_small_logo = goods_small_logo;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(Integer goods_price) {
        this.goods_price = goods_price;
    }

    public Integer getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(Integer goods_number) {
        this.goods_number = goods_number;
    }

    public Integer getGoods_weight() {
        return goods_weight;
    }

    public void setGoods_weight(Integer goods_weight) {
        this.goods_weight = goods_weight;
    }

    public Integer getGoods_state() {
        return goods_state;
    }

    public void setGoods_state(Integer goods_state) {
        this.goods_state = goods_state;
    }

    public Integer getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Integer add_time) {
        this.add_time = add_time;
    }

    public Integer getUpd_time() {
        return upd_time;
    }

    public void setUpd_time(Integer upd_time) {
        this.upd_time = upd_time;
    }

    public Integer getHot_mumber() {
        return hot_mumber;
    }

    public void setHot_mumber(Integer hot_mumber) {
        this.hot_mumber = hot_mumber;
    }

    public Integer getIs_promote() {
        return is_promote;
    }

    public void setIs_promote(Integer is_promote) {
        this.is_promote = is_promote;
    }

    public String getGoods_introduce() {
        return goods_introduce;
    }

    public void setGoods_introduce(String goods_introduce) {
        this.goods_introduce = goods_introduce;
    }

    public List<GoodPic> getPics() {
        return pics;
    }

    public void setPics(List<GoodPic> pics) {
        this.pics = pics;
    }

    public List<GoodAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<GoodAttr> attrs) {
        this.attrs = attrs;
    }

}
