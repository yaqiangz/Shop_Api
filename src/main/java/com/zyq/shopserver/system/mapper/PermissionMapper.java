package com.zyq.shopserver.system.mapper;

import com.zyq.shopserver.system.entity.PermissionList;
import com.zyq.shopserver.system.entity.PermissionTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    List<PermissionList> getAllPermissionsList();
    List<PermissionTree> getAllPermissionTree();
    List<PermissionTree> getChildrenByPid(@Param("id") Integer id);
}
