package com.zyq.shopserver.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zyq.shopserver.security.constants.SecurityConstants;
import com.zyq.shopserver.system.entity.Manager;
import com.zyq.shopserver.system.service.ManagerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(SecurityConstants.BASE_URL)
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping(value = "/users", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUsers(@RequestParam(value = "query", required = false) String username, Integer pagenum, Integer pagesize) {
        List<Manager> users = new ArrayList<>();
        List<Map> usersMap = new ArrayList<>();
        List<Manager> result = new ArrayList<>(); // 分页结果
        Map<String, Object> userInfo;
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if (pagenum != null && pagesize != null) {
            if (username == null)
                username = "";
            System.out.println("username: " + username);
            users = managerService.getUsers(username, pagenum, pagesize);
            int total = users.size();
            if (total > 0) {
                int startIndex = (pagenum - 1) * pagesize;
                for (int i = startIndex; i < pagesize + startIndex && i < users.size(); i++)
                    result.add(users.get(i));
                for (int i = 0; i < pagesize && i < result.size(); i++) {
                    userInfo = new LinkedHashMap<>();
                    userInfo.put("id", result.get(i).getMg_id());
                    userInfo.put("role_name", result.get(i).getRole_name());
                    userInfo.put("username", result.get(i).getMg_name());
                    userInfo.put("create_time", result.get(i).getMg_time());
                    userInfo.put("mobile", result.get(i).getMg_mobile());
                    userInfo.put("email", result.get(i).getMg_email());
                    userInfo.put("mg_state", result.get(i).getMg_status());
                    usersMap.add(userInfo);
                }
            }
            dataMap.put("total", total);
            dataMap.put("pagenum", pagenum);
            dataMap.put("users", usersMap);
            metaMap.put("msg", "获取管理员列表成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        } else if (pagenum == null || pagesize == null) {
            if (pagesize == null)
                metaMap.put("msg", "pagesize参数错误");
            if (pagenum == null)
                metaMap.put("msg", "pagenum参数错误");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
            dataMap = null;
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
    @ResponseBody
    @PostMapping(value = "/users", produces = "application/json;charset=utf-8")
    public String addUser(String username, String password, String email, String mobile) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        if (password == null || username == null) {
            if (password == null)
                metaMap.put("msg", "密码不能为空");
            if (username == null)
                metaMap.put("msg", "用户名不能为空");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
            dataMap = null;
        } else {
            Manager userByUsername = managerService.findUserByUsername(username);
            if (userByUsername != null) {
                metaMap.put("msg", "该用户已存在");
                metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
                dataMap = null;
            } else {
                if (email == null)
                    email = "";
                if (mobile == null)
                    mobile = "";
                Manager manager = new Manager();
                manager.setMg_name(username);
                manager.setMg_pwd(passwordEncoder.encode(password));
                manager.setMg_mobile(mobile);
                manager.setMg_email(email);
                manager.setRole_id(31);
                manager.setMg_status(1);
                manager.setMg_time((int) new Date().getTime());
                managerService.addUser(manager);
                userByUsername = managerService.findUserByUsername(username);
                dataMap.put("id", userByUsername.getMg_id());
                dataMap.put("rid", userByUsername.getRole_id());
                dataMap.put("username", username);
                dataMap.put("mobile", userByUsername.getMg_mobile());
                dataMap.put("email", userByUsername.getMg_email());
                metaMap.put("msg", "用户创建成功");
                metaMap.put("status", HttpServletResponse.SC_CREATED);
            }
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
    @ResponseBody
    @PutMapping(value = "/users/{uid}/state/{type}", produces = "application/json;charset=utf-8")
    public String editUserState(@PathVariable("uid") Integer uid,@PathVariable("type") Integer type) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Integer editResult = managerService.editUserState(uid, type);
        if (editResult == 0) {
            dataMap = null;
            metaMap.put("msg", "管理员ID不存在");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Manager userByUserId = managerService.findUserByUserId(uid);
            dataMap.put("id", userByUserId.getMg_id());
            dataMap.put("rid", userByUserId.getRole_id());
            dataMap.put("username", userByUserId.getMg_name());
            dataMap.put("mobile", userByUserId.getMg_mobile());
            dataMap.put("email", userByUserId.getMg_email());
            dataMap.put("mg_state", userByUserId.getMg_status());
            metaMap.put("msg", "设置状态成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }

    @ResponseBody
    @GetMapping(value = "/users/{id}", produces = "application/json;charset=utf-8")
    public String getUserById(@PathVariable("id") Integer id) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Manager userByUserId = managerService.findUserByUserId(id);
        if (userByUserId == null) {
            dataMap = null;
            metaMap.put("msg", "管理员ID不存在");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            dataMap.put("id", userByUserId.getMg_id());
            dataMap.put("username", userByUserId.getMg_name());
            dataMap.put("role_id", userByUserId.getRole_id());
            dataMap.put("mobile", userByUserId.getMg_mobile());
            dataMap.put("email", userByUserId.getMg_email());
            metaMap.put("msg", "查询成功");
            metaMap.put("msg", HttpServletResponse.SC_OK);
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
}
