package com.example.seata1.controller;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * 启动Seata1和Seata2服务
 * <p>
 * 当调用接口：
 * http://localhost:8081/user-service/user/account/reduce 的时候会爆出500内部错误。
 * 这时候检查一下数据源或者seata-server的console你会发现数据没有变化，console出现了两个branchId对应的doRollback输出。再看看undo_log表，自增ID从1变成了2.
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("account/reduce")
    @GlobalTransactional(timeoutMills = 30000, rollbackFor = Exception.class)
    //在事务的入口方法添加Seata注解,被调用方不需要加注解
    public String reduceAccount() {
        int update = jdbcTemplate.update("update t_user set account = account - 1 where id = 1");
        System.out.println("分布式事务验证:" + update);

        restTemplate.getForEntity("http://localhost:8082/good-service/good/amount/reduce", String.class);
        return "success";
    }
}
