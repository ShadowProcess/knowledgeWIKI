package com.example.jpademo.controller;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaQueryDslController {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @GetMapping("dsl")
    public String s() {
        return "dsl";
    }
}
