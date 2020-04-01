package com.zyq.shopserver.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

//@JsonIgnoreProperties(value = {"ps_level"})
public class PermissionTree {
    public interface SimpleView{};

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
    private Object pid;
    @JSONField(ordinal = 6)
    private List<PermissionTree> children;
    @JSONField(ordinal = 7)
    private Integer order;

    @Override
    public String toString() {
        return "PermissionTree{" +
                "id=" + id +
                ", authName='" + authName + '\'' +
                ", level='" + level + '\'' +
                ", path='" + path + '\'' +
                ", pid='" + pid + '\'' +
                ", children=" + children +
                ", order=" + order +
                '}';
    }

    @JsonView(SimpleView.class)
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    @JsonView(SimpleView.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @JsonView(SimpleView.class)
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
    @JsonView(SimpleView.class)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getPid() {
        return pid;
    }

    public void setPid(Object pid) {
        this.pid = pid;
    }
    @JsonView(SimpleView.class)
    public List<PermissionTree> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionTree> children) {
        this.children = children;
    }
}
