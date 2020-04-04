package com.zyq.shopserver.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private Integer roleId;
    private String roleName;
    @JsonIgnore
    private String ps_ids;
    @JsonIgnore
    private String ps_ca;
    private String roleDesc;
    private List<PermissionTree> children;
    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", ps_ids='" + ps_ids + '\'' +
                ", ps_ca='" + ps_ca + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", children=" + children +
                '}';
    }

    public Integer getId() {
        return roleId;
    }

    public void setId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPs_ids() {
        return ps_ids;
    }

    public void setPs_ids(String ps_ids) {
        this.ps_ids = ps_ids;
    }

    public String getPs_ca() {
        return ps_ca;
    }

    public void setPs_ca(String ps_ca) {
        this.ps_ca = ps_ca;
    }

    public String getroleDesc() {
        return roleDesc;
    }

    public void setroleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<PermissionTree> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionTree> permissions) {
        this.children = permissions;
    }
}