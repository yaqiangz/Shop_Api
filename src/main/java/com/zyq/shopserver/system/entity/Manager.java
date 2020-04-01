package com.zyq.shopserver.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private Integer mg_id;
    private String mg_name;
    private String mg_pwd;
    private Integer mg_time;
    private Integer role_id;
    private String mg_mobile;
    private String mg_email;
    private Integer mg_state;
    private String role_name;

    public Manager(Integer mg_id, String mg_name, String mg_pwd, Integer mg_time, Integer role_id, String mg_mobile, String mg_email, Integer mg_state) {
        this.mg_id = mg_id;
        this.mg_name = mg_name;
        this.mg_pwd = mg_pwd;
        this.mg_time = mg_time;
        this.role_id = role_id;
        this.mg_mobile = mg_mobile;
        this.mg_email = mg_email;
        this.mg_state = mg_state;
    }

    public Manager() {
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "mg_id=" + mg_id +
                ", mg_name='" + mg_name + '\'' +
                ", mg_pwd='" + mg_pwd + '\'' +
                ", mg_time=" + mg_time +
                ", role_id=" + role_id +
                ", mg_mobile='" + mg_mobile + '\'' +
                ", mg_email='" + mg_email + '\'' +
                ", mg_state=" + mg_state +
                '}';
    }

    public Integer getMg_id() {
        return mg_id;
    }

    public void setMg_id(Integer mg_id) {
        this.mg_id = mg_id;
    }

    public String getMg_name() {
        return mg_name;
    }

    public void setMg_name(String mg_name) {
        this.mg_name = mg_name;
    }

    public String getMg_pwd() {
        return mg_pwd;
    }

    public void setMg_pwd(String mg_pwd) {
        this.mg_pwd = mg_pwd;
    }

    public Integer getMg_time() {
        return mg_time;
    }

    public void setMg_time(Integer mg_time) {
        this.mg_time = mg_time;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getMg_mobile() {
        return mg_mobile;
    }

    public void setMg_mobile(String mg_mobile) {
        this.mg_mobile = mg_mobile;
    }

    public String getMg_email() {
        return mg_email;
    }

    public void setMg_email(String mg_email) {
        this.mg_email = mg_email;
    }

    public Integer getMg_status() {
        return mg_state;
    }
    public void setMg_status(Integer mg_state) {
        this.mg_state = mg_state;
    }

    public List<SimpleGrantedAuthority> getRoles() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role_name));
        return authorities;
    }
}
