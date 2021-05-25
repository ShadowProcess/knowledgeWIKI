package com.example.seata2springboot.controller;

import com.example.seata2springboot.exception.GlobalTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 使用全局异常中抛出异常，来解决分布式事务失效问题
 */

@Slf4j
@RestController
@RequestMapping("good1")
public class GoodController1 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("amount/reduce1")
    public String reduceAmount1() {
        log.info("查看Sleuth链路传递");
        jdbcTemplate.update("update t_good set amount = amount - 1 where id = 1");
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new GlobalTransactionException("111", "111");
        }
        return "success";
    }

}
