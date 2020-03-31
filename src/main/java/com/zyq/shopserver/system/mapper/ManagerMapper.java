package com.zyq.shopserver.system.mapper;

import com.zyq.shopserver.system.entity.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerMapper {
    Manager findUserByUsername(String username);
    Manager findUserByUserId(Integer id);
    List<Manager> getUsers(@Param("query") String query);
    Integer addUser(@Param("manager") Manager manager);
    Integer editUserState(@Param("uid") Integer uid, @Param("state") Integer state);
    Integer editUserInfo(@Param("id") Integer uid, @Param("email") String email, @Param("mobile") String mobile);
    Integer deleteUser(@Param("id") Integer id);
    Integer editUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);
}
