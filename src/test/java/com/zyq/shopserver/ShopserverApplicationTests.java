package com.zyq.shopserver;

import com.zyq.shopserver.system.entity.PermissionList;
import com.zyq.shopserver.system.entity.PermissionTree;
import com.zyq.shopserver.system.service.ManagerService;
import com.zyq.shopserver.system.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShopserverApplicationTests {
    @Autowired
    ManagerService managerService;
    @Autowired
    PermissionService permissionService;
    @Test
    void contextLoads() {
        int count = 0;
        List<PermissionTree> allPermissionTree = permissionService.getMenuPermissionTree();
        for (PermissionTree permissionTree : allPermissionTree) {
            System.out.println(permissionTree);
        }
        System.out.println(allPermissionTree.size());
    }

}
