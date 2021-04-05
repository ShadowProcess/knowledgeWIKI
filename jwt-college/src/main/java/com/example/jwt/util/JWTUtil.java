package com.example.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt token泄露了怎么办？
 * 前面的文章下有不少人留言提到这个问题，我则认为这不是问题。传统的 session+cookie 方案，
 * 如果泄露了 sessionId，别人同样可以盗用你的身份。扬汤止沸不如釜底抽薪，不妨来追根溯源一下，什么场景会导致你的 jwt 泄露。
 *
 * 遵循如下的实践可以尽可能保护你的 jwt 不被泄露：使用 https 加密你的应用，
 * 返回 jwt 给客户端时设置 httpOnly=true 并且使用 cookie 而不是 LocalStorage 存储 jwt，
 * 这样可以防止 XSS 攻击和 CSRF 攻击（对这两种攻击感兴趣的童鞋可以看下 spring security 中对他们的介绍CSRF,XSS）
 *
 * 你要是正在使用 jwt 访问一个接口，这个时候你的同事跑过来把你的 jwt 抄走了，这种泄露，恕在下无力
 */

/**
 * ————————————————
 * 如果cookie中设置了HttpOnly属性，那么通过js脚本将无法读取到cookie信息，
 * 这样能有效的防止XSS攻击，窃取cookie内容，这样就增加了cookie的安全性，
 * 即便是这样，也不要将重要信息存入cookie。XSS全称Cross SiteScript，跨站脚本攻击，
 * 是Web程序中常见的漏洞，XSS属于被动式且用于客户端的攻击方式，所以容易被忽略其危害性。
 * 其原理是攻击者向有XSS漏洞的网站中输入(传入)恶意的HTML代码，当其它用户浏览该网站时，
 * 这段HTML代码会自动执行，从而达到攻击的目的。如，盗取用户Cookie、破坏页面结构、重定向到其它网站等。
 * ————————————————
 *
 * 标准中注册的声明 (建议但不强制使用) ：
 * iss: jwt签发者
 * sub: 面向的用户(jwt所面向的用户)
 * aud: 接收jwt的一方
 * exp: 过期时间戳(jwt的过期时间，这个过期时间必须要大于签发时间)
 * nbf: 定义在什么时间之前，该jwt都是不可用的.
 * iat: jwt的签发时间
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 */

/**
 * JWT不适合做登录权限管理
 * 再说说JWT一般用于单次、具有时效性的场景，主要特点就是防篡改，就比如车票，只能用一次而已，用过的车票就丢弃了；
 *
 * 用户如果正在操作，突然token过期了，跳转到登录页，那么用户体验非常不好？
 * 通过设置jwt过期时间一个思路解决这个体验问题：
 * 根据当前时间计算到今天晚上凌晨3-4点，这个时间段过期，因为用户一般在凌晨3-4点这个时间段，一般都在睡觉
 */

public class JWTUtil {
    //日志
    private static Logger log = LoggerFactory.getLogger(JWTUtil.class);

    //token 密钥
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQKsdfkjsdrow32234545fdf>?N<:{LWPW";
    //15分钟超时时间,TOKEN过期后，解析会失败，因为解析方法会校验时间，如果过期，将抛出异常，提示已过期
    private static final long EXPIRE = 60 * 1000;


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
                    .withIssuer("令牌签发者")
                    .withSubject("主题")
                    .withClaim("userId", userId)
                    .withClaim("company","腾讯")
                    .withExpiresAt(expiration_time) //定义token有效期
                    .sign(algorithm);   //签名算法指定
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
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            decodedJWT = verifier.verify(token);
            log.info("超时时间:" + decodedJWT.getExpiresAt());
            log.info("所有载体信息:" + decodedJWT.getClaims());
            log.info("算法:" + decodedJWT.getAlgorithm());
        } catch (TokenExpiredException e) {
            log.info("token已过期");
            return null;
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

        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiLkuLvpopgiLCJpc3MiOiLlj5HooYzkuroiLCJjb21wYW55Ijoi6IW-6K6vIiwiZXhwIjoxNjE3NjE2NDU3LCJ1c2VySWQiOiJhbGV4In0.5yq0FDcrfjohutQCT8QGS9cb6LUW9kMTOqJNLw35izk";
        DecodedJWT decodedJWT = verifyToken(token);
        System.out.println(decodedJWT);
    }
}
