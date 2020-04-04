package com.zyq.shopserver;

import com.zyq.shopserver.system.entity.PermissionList;
import com.zyq.shopserver.system.entity.PermissionTree;
import com.zyq.shopserver.system.entity.Role;
import com.zyq.shopserver.system.service.ManagerService;
import com.zyq.shopserver.system.service.PermissionService;
import com.zyq.shopserver.system.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShopserverApplicationTests {
    @Autowired
    RoleService roleService;
    @Test
    void contextLoads() {
    }

}
