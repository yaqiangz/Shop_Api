package com.zyq.shopserver.security.entity;

import com.zyq.shopserver.security.service.CustomUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    private final CustomUserDetailsService userDetailsService;

    public CurrentUser(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public JwtUser getCurrentUser() {
        return (JwtUser) userDetailsService.loadUserByUsername(getCurrentUserName());
    }

    // TODO: 采用该方法原因是CustomUserDetailsService注入失败
    public static String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null)
            return (String) authentication.getPrincipal();
        return null;
    }
}
