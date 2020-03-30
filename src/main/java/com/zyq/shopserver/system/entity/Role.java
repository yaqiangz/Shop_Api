package com.zyq.shopserver.system.entity;

public class Role {
    private Integer role_id;
    private String role_name;
    private String ps_ids;
    private String ps_ca;
    private String role_desc;

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", ps_ids='" + ps_ids + '\'' +
                ", ps_ca='" + ps_ca + '\'' +
                ", role_desc='" + role_desc + '\'' +
                '}';
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
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

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }
}