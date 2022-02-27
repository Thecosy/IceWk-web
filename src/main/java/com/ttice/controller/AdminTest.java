package com.ttice.controller;

import com.ttice.entity.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttice.entity.Test;

@RestController
@RequestMapping("/test")
public class AdminTest {

    @GetMapping
    public Test test20000() {
        Test user = new Test();
        user.setCode(20000);
        user.setMes("zhanghaoliang");

        return user;

    }
    @GetMapping("/article")
    public Article test() {
        Article user = new Article();
        user.setContent("200");
        return user;

    }
}
