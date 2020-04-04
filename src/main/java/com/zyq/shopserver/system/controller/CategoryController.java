package com.zyq.shopserver.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyq.shopserver.security.constants.SecurityConstants;
import com.zyq.shopserver.system.entity.Category;
import com.zyq.shopserver.system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @PostMapping(value = "/categories", produces = "application/json;charset=utf-8")
    public String addCategory(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Category category = objectMapper.readValue(request.getInputStream(), Category.class);
        if (category.getCat_name() == null || category.getCat_level() == null || category.getCat_pid() == null) {
            dataMap = null;
            if (category.getCat_level() == null)
                metaMap.put("msg", "必须提供分类层级");
            if (category.getCat_pid() == null)
                metaMap.put("msg", "必须提供分类父ID");
            if (category.getCat_name() == null)
                metaMap.put("msg", "必须提供分类名称");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Integer integer = categoryService.addCategory(category);
            if (integer == 0) {
                dataMap = null;
                metaMap.put("msg", "添加失败");
                metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
            } else {
                category = categoryService.getCategoryById(category.getCat_id());
                dataMap.put("cat_id", category.getCat_id());
                dataMap.put("cat_name", category.getCat_name());
                dataMap.put("cat_pid", category.getCat_pid());
                dataMap.put("cat_level", category.getCat_level());
                metaMap.put("msg", "创建成功");
                metaMap.put("status", HttpServletResponse.SC_CREATED);
            }
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
    @GetMapping(value = "/categories/{id}", produces = "application/json;charset=utf-8")
    public String getCategory(@PathVariable("id") Integer catId) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Category categoryById = categoryService.getCategoryById(catId);
        if (categoryById == null) {
            dataMap = null;
            metaMap.put("msg", "分类ID不存在");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            dataMap.put("cat_id", categoryById.getCat_id());
            dataMap.put("cat_name)", categoryById.getCat_name());
            dataMap.put("cat_pid", categoryById.getCat_pid());
            dataMap.put("cat_leve", categoryById.getCat_level());
            metaMap.put("msg", "获取成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }

    @PutMapping(value = "/categories/{id}", produces = "application/json;charset=utf-8")
    public String editCategory(@PathVariable("id") Integer catId, HttpServletResponse  response, HttpServletRequest request) throws IOException {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Category category = mapper.readValue(request.getInputStream(), Category.class);
        if (category.getCat_name() == null) {
            dataMap = null;
            metaMap.put("msg", "分类名称不能为空");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Integer integer = categoryService.editCategory(category.getCat_name(), catId);
            if (integer == 0) {
                dataMap = null;
                metaMap.put("msg", "更新失败");
                metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
            } else {
                category = categoryService.getCategoryById(catId);
                dataMap.put("cat_id", category.getCat_id());
                dataMap.put("cat_name", category.getCat_name());
                dataMap.put("cat_pid", category.getCat_pid());
                dataMap.put("cat_level", category.getCat_level());
                metaMap.put("msg", "更新成功");
                metaMap.put("status", HttpServletResponse.SC_OK);
            }
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
    @DeleteMapping(value = "/categories/{id}", produces = "application/json;charset=utf-8")
    public String deleteCategory(@PathVariable("id") Integer catId) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = null;
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Integer integer = categoryService.deleteCategory(catId);
        if (integer == 0) {
            metaMap.put("msg", "删除失败");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            metaMap.put("msg", "删除成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
}
