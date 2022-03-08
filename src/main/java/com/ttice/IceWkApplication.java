package com.ttice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@MapperScan("com.ttice.mapper")
public class IceWkApplication {

    public static void main(String[] args) {
        SpringApplication.run(IceWkApplication.class, args);
    }

}
