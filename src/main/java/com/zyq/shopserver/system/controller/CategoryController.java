package com.zyq.shopserver.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zyq.shopserver.security.constants.SecurityConstants;
import com.zyq.shopserver.system.entity.Category;
import com.zyq.shopserver.system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(SecurityConstants.BASE_URL)
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping(value = "/categories", produces = "application/json;charset=utf-8")
    public String getAllCategories(Integer type, Integer pagenum, Integer pagesize) {
        List<Category> result = new ArrayList<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if (type == null)
            type = 3;
        List<Category> allCategories = categoryService.getAllCategories();
        if (type == 1) {
            for (Category allCategory : allCategories) {
                allCategory.setChildren(null);
            }
        } else if (type ==2) {
            for (Category allCategory : allCategories) {
                List<Category> children = allCategory.getChildren();
                for (Category child : children) {
                    child.setChildren(null);
                }
                allCategory.setChildren(children);
            }
        }
        if (pagenum != null && pagesize != null) {
            int startIndex = (pagenum - 1) * pagesize;
            for (int i = startIndex; i < pagesize + startIndex && i < allCategories.size(); i++)
                result.add(allCategories.get(i));
            dataMap.put("total", allCategories.size());
            dataMap.put("pagenum", pagenum);
            dataMap.put("pagesize", pagesize);
            dataMap.put("result", result);
        } else {
            dataMap.put("data", allCategories);
        }
        metaMap.put("msg", "获取成功");
        metaMap.put("status", HttpServletResponse.SC_OK);
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap);
    }
}
