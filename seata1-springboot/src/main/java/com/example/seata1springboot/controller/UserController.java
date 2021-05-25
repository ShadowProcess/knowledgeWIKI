package com.example.seata1springboot.controller;

import com.example.seata1springboot.aop.Global;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * <p>
 * 当调用接口：
 * http://localhost:8081/user-service/user/account/reduce 的时候会爆出500内部错误。
 * 这时候检查一下数据源你会发现数据没有变化，
 * 而且seata-server的console出现了两个branchId对应的doRollback输出。
 */

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("account/reduce")
    //@GlobalTransactional(name = "Alien",timeoutMills = 5000, rollbackFor = Exception.class)
    //name:全局事务名称,在事务的入口方法添加Seata注解,被调用方不需要加注解
    @Global
    public String reduceAccount() {
        int update = jdbcTemplate.update("update t_user set account = account - 1 where id = 1");

        String xid = RootContext.getXID();
        System.out.println("分布式事务id:" + xid);

        HttpHeaders headers = new HttpHeaders();
        headers.add(RootContext.KEY_XID, xid);

        String forEntity = restTemplate.postForObject("http://localhost:8082/good-service/good/amount/reduce", new HttpEntity<String>(headers), String.class);
        log.info("接口返回:{}", forEntity);
        return forEntity;
    }












    /**
     * 带header的GET请求
     */
    public void getHasHeader() {
        long userId = 32L;
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "123");
        ResponseEntity<String> response = restTemplate.exchange(
                "http://127.0.0.1:8280/user/{id}",
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class,
                userId);
        System.out.println(response.getBody());
    }
}
