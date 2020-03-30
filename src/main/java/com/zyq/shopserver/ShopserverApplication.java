package com.zyq.shopserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zyq.shopserver.system.mapper")
@SpringBootApplication
public class ShopserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopserverApplication.class, args);
    }

}
