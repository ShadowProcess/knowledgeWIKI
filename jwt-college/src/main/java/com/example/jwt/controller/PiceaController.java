package com.example.jwt.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.constant.ConstantKey;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PiceaController {

    @RequestMapping("/login")
    public Object login(String name,String password) throws Exception{
        String token = "";
        token = JWT.create()
                .withAudience(name) //将user id保存到token里
                .withExpiresAt(new Date(System.currentTimeMillis()+2*60*1000)) //定义token有效期
                .sign(Algorithm.HMAC256(ConstantKey.PICEA_JWT_KEY)); //加密秘钥，也可以使用用户保持在数据库中的密码字符串
        return token;
    }


    @RequestMapping("/queryPicea")
    public String queryPicea(){
        String ret = "通过验证";
        return ret;
    }

}
