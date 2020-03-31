package com.zyq.shopserver.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zyq.shopserver.security.constants.SecurityConstants;
import com.zyq.shopserver.system.entity.PermissionList;
import com.zyq.shopserver.system.entity.PermissionTree;
import com.zyq.shopserver.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(SecurityConstants.BASE_URL)
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    @GetMapping(value = "/rights/{type}", produces = "application/json;charset=utf-8")
    public String getAllPermissions(@PathVariable("type") String type) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        if (type.equals("list")) {
            List<PermissionList> listPermissionList = permissionService.getAllPermissionsList();
            resultMap.put("data", listPermissionList);
            metaMap.put("msg", "获取权限列表成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        } else if (type.equals("tree")) {
            List<PermissionTree> allPermissionTree = permissionService.getAllPermissionTree();
            resultMap.put("data", allPermissionTree);
            metaMap.put("msg", "获取权限列表成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        } else {
            resultMap.put("data", null);
            metaMap.put("msg", "显示类型参数错误");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        }
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
}
