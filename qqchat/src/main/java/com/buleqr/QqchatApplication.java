package com.buleqr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {
        "com.buleqr.mapper"
})
public class QqchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(QqchatApplication.class, args);
    }

}
