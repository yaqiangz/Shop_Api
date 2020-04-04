package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.Category;
import com.zyq.shopserver.system.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.getAllCategories();
    }
}
