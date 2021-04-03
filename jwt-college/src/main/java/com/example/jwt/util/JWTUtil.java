package com.example.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    //token 密钥
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQKsdfkjsdrow32234545fdf>?N<:{LWPW";
    //15分钟超时时间,TOKEN过期后，解析会失败，因为解析方法会校验时间，如果过期，将抛出异常，提示已过期
    private static final long EXPIRE = 60 * 1000;
    private static final String USER_ID = "USER_ID";
    //日志
    private static Logger log = LoggerFactory.getLogger(JWTUtil.class);

    /**
     * 加密
     *
     * @param userId
     * @return
     */
    public static String sign(String userId) {
        try {
            Date expiration_time = new Date(System.currentTimeMillis() + EXPIRE);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Map<String, Object> headerMap = new HashMap<>(2);
            headerMap.put("type", "JWT");
            headerMap.put("alg", "HS256");
            return JWT.create()
                    .withHeader(headerMap)
                    .withClaim(USER_ID, userId)    //将user的id保存到token里
                    .withExpiresAt(expiration_time) //定义token有效期
                    .sign(algorithm);   //加密秘钥，也可以使用用户保持在数据库中的密码字符串
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * 解密
     *
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token) {
        DecodedJWT decodedJWT = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            decodedJWT = verifier.verify(token);
            log.info("超时时间:" + decodedJWT.getExpiresAt());
            log.info("所有载体信息:" + decodedJWT.getClaims());
            log.info("指定的载体信息:" + decodedJWT.getClaim(USER_ID).asString());
            log.info("算法:" + decodedJWT.getAlgorithm());
        } catch (Exception e) {
            //解码异常，过期也会则抛出异常
            log.error(e.getMessage());
            return null;
        }
        return decodedJWT;
    }

    public static void main(String[] args) {
        String alex = sign("alex");
        System.out.println(alex);

        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJVU0VSX0lEIjoiYWxleCIsImV4cCI6MTYxNzQ1NDc2NH0.j16lci14yvQ8YJ8Vx77LXkuPFBZkLjTC7qJs-Lw9unU";
        DecodedJWT decodedJWT = verifyToken(token);
        System.out.println(decodedJWT);
    }
}
