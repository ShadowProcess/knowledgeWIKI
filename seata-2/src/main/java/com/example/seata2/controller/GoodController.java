package com.example.seata2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("good")
public class GoodController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("amount/reduce")
    public String reduceAmount() {
        jdbcTemplate.update("update t_good set amount = amount - 1 where id = 1");

        int i = 1/0;

        return "success";
    }
}
