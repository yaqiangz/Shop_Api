package com.zyq.shopserver.system.mapper;

import com.zyq.shopserver.system.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<Role> getAllRoles();
    Integer addRole(@Param("role_name") String roleName, @Param("role_desc") String roleDesc);
    Role getRoleByName(@Param("role_name") String roleName);
    Role getRoleById(@Param("role_id") Integer roleId);
    Integer editRole(@Param("role") Role role);
    Integer deleteRole(@Param("role_id") Integer roleId);
}
