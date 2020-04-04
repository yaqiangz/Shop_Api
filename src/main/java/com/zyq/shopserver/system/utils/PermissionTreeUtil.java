package com.zyq.shopserver.system.utils;

import com.zyq.shopserver.system.entity.PermissionTree;

import java.util.ArrayList;
import java.util.List;

public class PermissionTreeUtil {
    public static List<PermissionTree> sort(List<PermissionTree> parent, List<PermissionTree> son) {
        for (int i = 0; i < son.size(); i++) {
            PermissionTree permissionTree = son.get(i);
            Object pid = permissionTree.getPid();
            for (int j = 0; j < parent.size(); j++) {
                PermissionTree permissionTree1 = parent.get(j);
                if (pid == permissionTree1.getId()) {
                    List<PermissionTree> children = permissionTree1.getChildren();
                    if (children == null)
                        children = new ArrayList<>();
                    children.add(permissionTree);
                    permissionTree1.setChildren(children);
                    parent.set(j, permissionTree1);
                    break;
                }
            }
            son.remove(i);
        }
        parent.addAll(son);
        return parent;
    }
}
