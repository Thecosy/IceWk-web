package com.ttice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ttice.mapper")
public class SpringshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringshopApplication.class, args);
    }

}
