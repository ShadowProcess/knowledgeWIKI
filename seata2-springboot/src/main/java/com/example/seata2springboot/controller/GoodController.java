package com.example.seata2springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("good")
public class GoodController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("amount/reduce")
    public String reduceAmount() {
        log.info("查看Sleuth链路传递");
        jdbcTemplate.update("update t_good set amount = amount - 1 where id = 1");

        int i = 1/0;

        return "success";
    }
}
