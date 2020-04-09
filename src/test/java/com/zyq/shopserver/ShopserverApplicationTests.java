package com.zyq.shopserver;

import com.zyq.shopserver.system.entity.Order;
import com.zyq.shopserver.system.entity.PermissionList;
import com.zyq.shopserver.system.entity.PermissionTree;
import com.zyq.shopserver.system.entity.Role;
import com.zyq.shopserver.system.service.ManagerService;
import com.zyq.shopserver.system.service.OrderService;
import com.zyq.shopserver.system.service.PermissionService;
import com.zyq.shopserver.system.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShopserverApplicationTests {
    @Autowired
    OrderService orderService;
    @Test
    void contextLoads() {
        Order order = new Order();
        order.setOrderId(42);
        order.setIsSend("是");
        order.setOrderPay("1");
        Integer integer = orderService.editOrder(order);
        System.out.println(integer);
    }

}
