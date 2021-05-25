package com.example.seata1springboot.controller;


import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * http://localhost:8081/user-service/user/account/reduce1 的时候会爆出500内部错误。
 *
 * 下游服务使用全局异常中抛出异常，来解决分布式事务失效问题
 */
@Slf4j
@RestController
@RequestMapping("user1")
public class UserController1 {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("account/reduce1")
    @GlobalTransactional(name = "Alien",timeoutMills = 5000, rollbackFor = Exception.class)
    public String reduceAccount1() {
        int update = jdbcTemplate.update("update t_user set account = account - 1 where id = 1");

        String xid = RootContext.getXID();
        System.out.println("分布式事务id:" + xid);

        HttpHeaders headers = new HttpHeaders();
        headers.add(RootContext.KEY_XID, xid);

        String forEntity = restTemplate.postForObject("http://localhost:8082/good-service/good1/amount/reduce1", new HttpEntity<String>(headers), String.class);
        log.info("接口返回:{}", forEntity);
        return forEntity;
    }
}
