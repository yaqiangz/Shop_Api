package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.PermissionList;
import com.zyq.shopserver.system.entity.PermissionTree;
import com.zyq.shopserver.system.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImp implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public List<PermissionList> getAllPermissionsList() {
        return permissionMapper.getAllPermissionsList();
    }

    @Override
    public List<PermissionTree> getAllPermissionTree() {
        return permissionMapper.getAllPermissionTree();
    }

    @Override
    public List<PermissionTree> getMenuPermissionTree() {
        return permissionMapper.getMenuPermissionTree();
    }
}
