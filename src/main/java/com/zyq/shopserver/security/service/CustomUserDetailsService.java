package com.zyq.shopserver.security.service;

import com.zyq.shopserver.security.entity.JwtUser;
import com.zyq.shopserver.system.entity.Manager;
import com.zyq.shopserver.system.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ManagerService managerService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Manager userByUsername = managerService.findUserByUsername(username);
//        if (userByUsername == null) {
//            System.out.println("here");
//            throw new UsernameNotFoundException("not found");
//        }
//        System.out.println("username: " + userByUsername.getMg_name());
//        System.out.println("pass: " + userByUsername.getMg_pwd());
//        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + userByUsername.getRole_name());
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(simpleGrantedAuthority);
//        return new org.springframework.security.core.userdetails.User(username, userByUsername.getMg_pwd(), authorities);

        Manager manager = managerService.findUserByUsername(username);
        return new JwtUser(manager);
    }
}
