package com.example.jwt.controller;

import com.example.jwt.util.JWTUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PiceaController {

    @PostMapping("/login")
    public Object login(String name, String password) {
        System.out.println("检测账号密码正确之后,返回token,用户每次请求,请求头都需要带着token");
        return JWTUtil.sign("用户id");
    }


    @RequestMapping("/queryPicea")
    public String queryPicea() {
        String ret = "通过验证";
        return ret;
    }

}
