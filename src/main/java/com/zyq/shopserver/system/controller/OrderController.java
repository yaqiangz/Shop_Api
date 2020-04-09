package com.zyq.shopserver.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyq.shopserver.security.constants.SecurityConstants;
import com.zyq.shopserver.system.entity.Order;
import com.zyq.shopserver.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(SecurityConstants.BASE_URL)
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping(value = "/orders", produces = "application/json;charset=utf-8")
    public String getAllOrders(@RequestParam(name = "pagenum", required = false) Integer pageNum, @RequestParam(name = "pagesize", required = false) Integer pageSize) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        if (pageNum == null) {
            dataMap = null;
            metaMap.put("msg", "pagenum参数错误");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else if (pageSize == null) {
            dataMap = null;
            metaMap.put("msg", "pagesize参数错误");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Integer ordersCount = orderService.getOrdersCount();
            List<Order> allOrders = orderService.getAllOrders(pageNum, pageSize);
            for (Order allOrder : allOrders) {
                System.out.println(allOrder);
            }
            dataMap.put("total", ordersCount);
            dataMap.put("pagenum", pageNum);
            dataMap.put("goods", allOrders);
            metaMap.put("msg", "获取成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap);
    }
    @PutMapping(value = "/orders/{id}", produces = "application/json;charset=utf-8")
    public String editOrder(@PathVariable("id") Integer orderId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(request.getInputStream(), Order.class);
        if (order.getPayStatus() == null && order.getIsSend() == null && order.getOrderPay() == null && order.getOrderPrice() == null && order.getOrderNumber() == null) {
            resultMap.put("data", null);
            metaMap.put("msg", "修改参数全为空");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            order.setOrderId(orderId);
            Integer integer = orderService.editOrder(order);
            if (integer == 0) {
                resultMap.put("data", null);
                metaMap.put("msg", "修改失败");
                metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
            } else {
                Order orderById = orderService.getOrderById(orderId);
                resultMap.put("data", orderById);
                metaMap.put("msg", "修改成功");
                metaMap.put("statsu", HttpServletResponse.SC_OK);
            }
        }
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
    @GetMapping(value = "/orders/{id}", produces = "application/json;charset=utf-8")
    public String getOrderDetails(@PathVariable("id") Integer orderId) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Order orderById = orderService.getOrderById(orderId);
        if (orderById == null) {
            dataMap = null;
            metaMap.put("msg", "订单ID不存在");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            dataMap.put("data", orderById);
            metaMap.put("msg", "获取成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
    @PutMapping(value = "/orders/{id}/addr", produces = "application/json;charset=utf-8")
    public String editAddr(@PathVariable("id") Integer orderId, @RequestBody Map<String, Object> map) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        String consigneeAddr = (String) map.get("consignee_addr");
        if (consigneeAddr == null) {
            resultMap.put("data", null);
            metaMap.put("msg", "未输入地址");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Integer integer = orderService.editAddrByOrderId(consigneeAddr, orderId);
            if (integer == 0) {
                resultMap.put("data", null);
                metaMap.put("msg", "修改失败");
                metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
            } else {
                Order orderById = orderService.getOrderById(orderId);
                resultMap.put("data", orderById);
                metaMap.put("msg", "修改成功");
                metaMap.put("status", HttpServletResponse.SC_OK);
            }
        }
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
}
