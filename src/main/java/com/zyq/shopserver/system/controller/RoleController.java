package com.zyq.shopserver.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.net.HttpResponse;
import com.zyq.shopserver.security.constants.SecurityConstants;
import com.zyq.shopserver.system.entity.PermissionTree;
import com.zyq.shopserver.system.entity.Role;
import com.zyq.shopserver.system.service.RoleService;
import com.zyq.shopserver.system.utils.PermissionTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(SecurityConstants.BASE_URL)
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping(value = "/roles", produces = "application/json;charset=utf-8")
    public Map<String, Object> getRolesPermission() {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        List<Role> allRoles = roleService.getAllRoles();
        List<PermissionTree> zeroPermission = new ArrayList<>();
        List<PermissionTree> onePermission = new ArrayList<>();
        List<PermissionTree> twoPermission = new ArrayList<>();
        for (Role allRole : allRoles) {
            List<PermissionTree> children = allRole.getChildren();
            zeroPermission = new ArrayList<>();
            onePermission = new ArrayList<>();
            twoPermission = new ArrayList<>();
            for (int i = 0; i < children.size(); i++) {
                PermissionTree permissionTree = children.get(i);
                String level = permissionTree.getLevel();
                switch (level) {
                    case "0":
                        zeroPermission.add(permissionTree);
                        break;
                    case "1":
                        onePermission.add(permissionTree);
                        break;
                    default:
                        twoPermission.add(permissionTree);
                        break;
                }
            }
            onePermission = PermissionTreeUtil.sort(onePermission, twoPermission);
            zeroPermission = PermissionTreeUtil.sort(zeroPermission, onePermission);
            children.clear();
            allRole.setChildren(zeroPermission);
        }
        resultMap.put("data", allRoles);
        metaMap.put("msg", "获取成功");
        metaMap.put("status", HttpServletResponse.SC_OK);
        resultMap.put("meta", metaMap);
        return resultMap;
    }
    @PostMapping(value = "/roles", produces = "application/json;charset=utf-8")
    public Map<String, Object> addRole(HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        try {
            Role role = mapper.readValue(request.getInputStream(), Role.class);
            if (roleService.getRoleByName(role.getRoleName()) != null) {
                dataMap = null;
                metaMap.put("msg", "用户名不能为空");
                metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
            } else {
                if (role.getroleDesc() == null)
                    role.setroleDesc("");
                Integer integer = roleService.addRole(role.getRoleName(), role.getroleDesc());
                if (integer == 0) {
                    dataMap = null;
                    metaMap.put("msg", "添加失败");
                    metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    role = roleService.getRoleByName(role.getRoleName());
                    dataMap.put("roleId", role.getId());
                    dataMap.put("roleName", role.getRoleName());
                    dataMap.put("roleDesc", role.getroleDesc());
                    metaMap.put("msg", "创建成功");
                    metaMap.put("status", HttpServletResponse.SC_CREATED);
                }
            }
        } catch (IOException e) {
            dataMap = null;
            metaMap.put("msg", "输入格式有误");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return resultMap;
    }
    @GetMapping(value = "/roles/{id}", produces = "application/json;charset=utf-8")
    public Map<String, Object> getRoleById(@PathVariable("id") Integer roleId) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Role roleById = roleService.getRoleById(roleId);
        if (roleById == null) {
            dataMap = null;
            metaMap.put("msg", "角色ID不存在");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            dataMap.put("roleId", roleById.getId());
            dataMap.put("roleName", roleById.getRoleName());
            dataMap.put("roleDesc", roleById.getroleDesc());
            metaMap.put("msg", "获取成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return resultMap;
    }
    @PutMapping(value = "/roles/{id}", produces = "application/json;charset=utf-8")
    public Map<String, Object> editRole(@PathVariable("id") Integer roleId, HttpServletRequest request, HttpServletRequest reponse) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Role role = mapper.readValue(request.getInputStream(), Role.class);
            String roleName = role.getRoleName();
            if (roleName == null) {
                dataMap = null;
                metaMap.put("msg", "角色名称不能为空");
                metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
            } else {
                String desc = role.getroleDesc();
                if (desc == null)
                    desc = "";
                roleService.editRole(role);
                role = roleService.getRoleByName(roleName);
                dataMap.put("roleId", role.getId());
                dataMap.put("roleName", role.getRoleName());
                dataMap.put("roleDesc", role.getroleDesc());
                metaMap.put("msg", "编辑成功");
                metaMap.put("status", HttpServletResponse.SC_OK);
            }
        } catch (IOException e) {
            dataMap = null;
            metaMap.put("msg", "输入参数格式不正确");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return resultMap;
    }
    @DeleteMapping(value = "/roles/{id}", produces = "application/json;charset=utf-8")
    public Map<String, Object> deleteRole(@PathVariable("id") Integer roleId) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Integer deleteResult = roleService.deleteRole(roleId);
        if (deleteResult == 0) {
            metaMap.put("msg", "角色ID不存在");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            metaMap.put("msg", "删除成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        }
        resultMap.put("data", null);
        resultMap.put("meta", metaMap);
        return resultMap;
    }
}
