package com.zyq.shopserver;

import com.zyq.shopserver.system.entity.Manager;
import com.zyq.shopserver.system.service.ManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ShopserverApplicationTests {
    @Autowired
    ManagerService managerService;
    @Test
    void contextLoads() {
        System.out.println(managerService.editUserState(509, 0));
    }

}
