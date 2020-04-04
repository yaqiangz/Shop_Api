package com.zyq.shopserver.system.mapper;

import com.zyq.shopserver.system.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryMapper {
    List<Category> getAllCategories();
    Integer addCategory(@Param("category") Category category);
    Category getCategoryById(@Param("cat_id") Integer id);
    Integer editCategory(@Param("cat_name") String catName, @Param("cat_id") Integer catId);
    Integer deleteCategory(@Param("cat_id") Integer catId);
}
