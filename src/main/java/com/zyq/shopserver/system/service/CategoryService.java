package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Integer addCategory(Category category);
    Category getCategoryById(Integer catId);
    Integer editCategory(String catName, Integer catId);
    Integer deleteCategory(Integer catId);
}
