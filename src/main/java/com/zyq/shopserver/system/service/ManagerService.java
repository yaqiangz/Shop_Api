package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.Manager;

import java.util.List;

public interface ManagerService {
    Manager findUserByUsername(String Username);
    Manager findUserByUserId(Integer id);
    List<Manager> getUsers(String query, Integer pagenum, Integer pagesize);
    Integer addUser(Manager manager);
    Integer editUserState(Integer uid, Integer state);
    Integer editUserInfo(Integer uid, String email, String mobile);
    Integer deleteUser(Integer id);
    Integer editUserRole(Integer uid, Integer rid);
}
