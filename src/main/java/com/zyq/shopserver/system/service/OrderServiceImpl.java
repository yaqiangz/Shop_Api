package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.Order;
import com.zyq.shopserver.system.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public List<Order> getAllOrders(Integer pagenum, Integer pagesize) {
        return orderMapper.getAllOrders((pagenum-1) * pagesize, pagesize);
    }

    @Override
    public Integer getOrdersCount() {
        return orderMapper.getOrdersCount();
    }

    @Override
    public Integer editOrder(Order order) {
        return orderMapper.editOrder(order);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        return orderMapper.getOrderById(orderId);
    }

    @Override
    public Integer editAddrByOrderId(String addr, Integer orderId) {
        return orderMapper.editAddrByOrderId(addr, orderId);
    }
}
