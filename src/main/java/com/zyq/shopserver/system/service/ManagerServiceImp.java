package com.zyq.shopserver.system.service;

import com.zyq.shopserver.system.entity.Manager;
import com.zyq.shopserver.system.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ManagerServiceImp implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public Manager findUserByUsername(String username) {
        return managerMapper.findUserByUsername(username);
    }

    @Override
    public Manager findUserByUserId(Integer id) {
        return managerMapper.findUserByUserId(id);
    }

    @Override
    public List<Manager> getUsers(String query, Integer pagenum, Integer pagesize) {
        return managerMapper.getUsers(query);
    }

    @Override
    public Integer addUser(Manager manager) {
        return managerMapper.addUser(manager);
    }

    @Override
    public Integer editUserState(Integer uid, Integer state) {
        return managerMapper.editUserState(uid, state);
    }
}
