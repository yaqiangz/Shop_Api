package com.zyq.shopserver.system.entity;

public class GoodPic {
    private Integer pics_id;
    private Integer goods_id;
    private String pics_big;
    private String pics_mid;
    private String pics_sma;
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "GoodPic{" +
                "pics_id=" + pics_id +
                ", goods_id=" + goods_id +
                ", pics_big='" + pics_big + '\'' +
                ", pics_mid='" + pics_mid + '\'' +
                ", pics_sma='" + pics_sma + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }

    public Integer getPics_id() {
        return pics_id;
    }

    public void setPics_id(Integer pics_id) {
        this.pics_id = pics_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getPics_big() {
        return pics_big;
    }

    public void setPics_big(String pics_big) {
        this.pics_big = pics_big;
    }

    public String getPics_mid() {
        return pics_mid;
    }

    public void setPics_mid(String pics_mid) {
        this.pics_mid = pics_mid;
    }

    public String getPics_sma() {
        return pics_sma;
    }

    public void setPics_sma(String pics_sma) {
        this.pics_sma = pics_sma;
    }

}
