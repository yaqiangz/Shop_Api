package com.zyq.shopserver.system.controller;

import com.zyq.shopserver.security.constants.SecurityConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SecurityConstants.BASE_URL)
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
