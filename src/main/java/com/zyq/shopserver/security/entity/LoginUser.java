package com.zyq.shopserver.security.entity;

public class LoginUser {
    private String username;
    private String password;
//    private Boolean rememberMe;

    public LoginUser() {
    }

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
//                ", rememberMe=" + rememberMe +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Boolean getRememberMe() {
//        return rememberMe;
//    }
//
//    public void setRememberMe(Boolean rememberMe) {
//        this.rememberMe = rememberMe;
//    }
}
