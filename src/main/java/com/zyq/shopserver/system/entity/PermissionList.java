package com.zyq.shopserver.system.entity;

public class PermissionList {
    private Integer id;
    private String authName;
    private Integer pid;
    private String level;
    private String path;


    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", authName='" + authName + '\'' +
                ", pid=" + pid +
                ", level='" + level + '\'' +
                ", path='" + path + '\'' +
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
}
