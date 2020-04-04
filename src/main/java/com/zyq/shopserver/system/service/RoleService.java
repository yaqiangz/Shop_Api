package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.Role;
import com.zyq.shopserver.system.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Integer addRole(String roleName, String roleDesc);
    Role getRoleByName(String roleName);
    Role getRoleById(Integer roleId);
    Integer editRole(Role role);
    Integer deleteRole(Integer roleId);
}
