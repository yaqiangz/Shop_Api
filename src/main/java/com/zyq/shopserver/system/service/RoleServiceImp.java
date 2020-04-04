package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.Role;
import com.zyq.shopserver.system.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public Integer addRole(String roleName, String roleDesc) {
        return roleMapper.addRole(roleName, roleDesc);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleMapper.getRoleByName(roleName);
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return roleMapper.getRoleById(roleId);
    }

    @Override
    public Integer editRole(Role role) {
        return roleMapper.editRole(role);
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        return roleMapper.deleteRole(roleId);
    }
}
