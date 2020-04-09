package com.zyq.shopserver.system.mapper;

import com.zyq.shopserver.system.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getAllOrders(@Param("offset") Integer pagenum, @Param("pagesize") Integer pagesize);
    Integer getOrdersCount();
    Integer editOrder(@Param("order") Order order);
    Order getOrderById(@Param("order_id") Integer orderId);
    Integer editAddrByOrderId(@Param("addr") String addr, @Param("order_id") Integer orderId);
}
