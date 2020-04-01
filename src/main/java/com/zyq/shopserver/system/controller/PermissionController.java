package com.zyq.shopserver.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonView;
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
import java.util.ArrayList;
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
            for (PermissionTree permissionTree : allPermissionTree) {
                List<PermissionTree> children = permissionTree.getChildren();
                for (PermissionTree child : children) {
                    List<PermissionTree> children1 = child.getChildren();
                    if (children1.size() == 0)
                        child.setChildren(null);
                    else {
                        Object pid1 = child.getPid();
                        for (PermissionTree tree : children1) {
                            tree.setChildren(null);
                            tree.setPid(pid1 + "," + tree.getPid());
                        }
                    }
                }
            }
            resultMap.put("data", allPermissionTree);
            metaMap.put("msg", "获取权限列表成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        } else {
            resultMap.put("data", null);
            metaMap.put("msg", "显示类型参数错误");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        }
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap);
    }
    @GetMapping(value = "/menus", produces = "application/json;charset=utf-8")
    @JsonView(PermissionTree.SimpleView.class)
    public Map<String, Object> getMenuPermissions() {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        List<PermissionTree> menuPermissionTree = permissionService.getMenuPermissionTree();
        for (PermissionTree permissionTree : menuPermissionTree) {
            List<PermissionTree> children = permissionTree.getChildren();
            for (PermissionTree child : children) {
                child.setChildren(new ArrayList<>());
            }
        }
        resultMap.put("data", menuPermissionTree);
        metaMap.put("msg", "获取菜单列表成功");
        metaMap.put("status", HttpServletResponse.SC_OK);
        resultMap.put("meta", metaMap);
        return resultMap;
    }
}
