package com.zyq.shopserver.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//@JsonIgnoreProperties(value = {"ps_level"})
public class PermissionTree {
    @JSONField(ordinal = 1)
    private Integer id;
    @JSONField(ordinal = 2)
    private String authName;
    //    @JSONField(ordinal = 5)
    @JSONField(serialize = false)
    private String level;
    @JSONField(ordinal = 3)
    private String path;
    @JSONField(ordinal = 4)
    private Integer pid;
    @JSONField(ordinal = 6)
    private List<PermissionTree> children;

    @Override
    public String toString() {
        return "PermissionTree{" +
                "id=" + id +
                ", authName='" + authName + '\'' +
                ", level='" + level + '\'' +
                ", path='" + path + '\'' +
                ", pid=" + pid +
                ", children=" + children +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<PermissionTree> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionTree> children) {
        this.children = children;
    }
}
