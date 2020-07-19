package com.example.springboottransaction.controller;


import com.example.springboottransaction.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping("/transfer")
    public String transferAccount(){
        try {
            accountService.transferAccounts(1,2,200);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

}
