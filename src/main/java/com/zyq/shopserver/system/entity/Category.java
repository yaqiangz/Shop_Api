package com.zyq.shopserver.system.entity;

import java.util.List;

public class Category {
    private Integer cat_id;
    private String cat_name;
    private Integer cat_pid;
    private Integer cat_level;
    private Boolean cat_deleted;
    private List<Category> children;

    @Override
    public String toString() {
        return "Category{" +
                "cat_id=" + cat_id +
                ", cat_name='" + cat_name + '\'' +
                ", cat_pid=" + cat_pid +
                ", cat_level=" + cat_level +
                ", cat_deleted=" + cat_deleted +
                ", children=" + children +
                '}';
    }

    public Integer getCat_id() {
        return cat_id;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public Integer getCat_pid() {
        return cat_pid;
    }

    public void setCat_pid(Integer cat_pid) {
        this.cat_pid = cat_pid;
    }

    public Integer getCat_level() {
        return cat_level;
    }

    public void setCat_level(Integer cat_level) {
        this.cat_level = cat_level;
    }

    public Boolean getCat_deleted() {
        return cat_deleted;
    }

    public void setCat_deleted(Boolean cat_deleted) {
        this.cat_deleted = cat_deleted;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
