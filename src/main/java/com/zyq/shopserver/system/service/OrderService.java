package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders(Integer pagenum, Integer pagesize);
    Integer getOrdersCount();
    Integer editOrder(Order order);
    Order getOrderById(Integer orderId);
    Integer editAddrByOrderId(String addr, Integer orderId);
}
