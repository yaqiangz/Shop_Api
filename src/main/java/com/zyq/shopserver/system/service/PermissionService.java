package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.PermissionList;
import com.zyq.shopserver.system.entity.PermissionTree;

import java.util.List;

public interface PermissionService {
    List<PermissionList> getAllPermissionsList();
    List<PermissionTree> getAllPermissionTree();
}
