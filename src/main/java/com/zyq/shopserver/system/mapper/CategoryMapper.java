package com.zyq.shopserver.system.mapper;

import com.zyq.shopserver.system.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryMapper {
    List<Category> getAllCategories();
}
