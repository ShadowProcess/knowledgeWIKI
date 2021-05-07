package com.example.apigateway.util;

import com.example.apigateway.enumeration.SignCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.UriEncoder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
public class AuthUtil {

    @Autowired
    RedisUtil redisUtil;

    private <T extends Map<String, Object>> T constructMsg(T rt, SignCode errorCode) {
        rt.put("code", errorCode.getCode());
        rt.put("msg", errorCode.getMessage());
        return rt;
    }

    /**
     * 判断鉴权
     *
     * @param result
     * @param accessKeyId
     * @param nonce
     * @param timestamp
     * @param signature
     * @param queryString
     * @return
     * @throws Exception
     */
    public boolean checkParam(Map<String, Object> result, String accessKeyId, String nonce, String timestamp, String signature, ServerHttpResponse response, ServerHttpRequest request, Map<Object, Object> objectMap, String uuid, String methodValue, String body) throws Exception {
        URI uri = request.getURI();
        //获取请求地址
        String requestURI = uri.getPath();
        //param
        String queryString = uri.getQuery();


        queryString = UriEncoder.decode(queryString);

        if (accessKeyId.length() != 32) {
            log.info("[{}],{}", uuid, SignCode.ACCESSKEY_ERROR.getMessage());
            constructMsg(result, SignCode.ACCESSKEY_ERROR);
            return false;
        }
        if (nonce.length() != 8) {
            log.info("[{}],{}", uuid, SignCode.NONCE_ERROR.getMessage());
            constructMsg(result, SignCode.NONCE_ERROR);
            return false;
        }
        if (timestamp.length() != 13) {
            log.info("[{}],{}", uuid, SignCode.TIMESTAMP_ERROR.getMessage());
            constructMsg(result, SignCode.TIMESTAMP_ERROR);
            return false;
        } else {
            long parseLong = Long.parseLong(timestamp);
            if (System.currentTimeMillis() - parseLong > (60000L * 5)) {
                log.info("[{}],{}", uuid, SignCode.TIMESTAMP_OUTOFDATE_ERROR.getMessage());
                constructMsg(result, SignCode.TIMESTAMP_OUTOFDATE_ERROR);
                return false;
            }
            Object nonceStr = redisUtil.get("openapi:" + accessKeyId + ":" + nonce);
            if (nonceStr != null) {
                if (nonce.equals(nonceStr.toString())) {
                    log.info("[{}],{}", uuid, SignCode.NONCE_EXIST.getMessage());
                    constructMsg(result, SignCode.NONCE_EXIST);
                    return false;
                }
            }
            redisUtil.set("openapi:" + accessKeyId + ":" + nonce, nonce, 60);
        }
        if (objectMap != null) {
            String accessKeySecret = objectMap.get("accessKeySecret").toString();
            log.info("[{}],nonce:{}", uuid, nonce);
            log.info("[{}],accessKeySecret:{}", uuid, accessKeySecret);
            log.info("[{}],timestamp:{}", uuid, timestamp);
            log.info("[{}],uri:{}", uuid, requestURI);
            String data = Signature.getOrignal(queryString, body, nonce, accessKeySecret, timestamp, requestURI, methodValue);
            String msgSignature = Signature.generate(data, accessKeySecret);
            log.info("[{}],signature 客户传入:{}", uuid, signature);
            log.info("[{}],signature 程序加密:{},待签名:{}", uuid, msgSignature, data);
            if (!msgSignature.equals(signature)) {
                log.info(SignCode.VALIDATESIGNATUREERROR.getMessage());
                constructMsg(result, SignCode.VALIDATESIGNATUREERROR);
                return false;
            } else {
                return true;
            }
        } else {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            log.info("[{}],{}", uuid, SignCode.ACCESSKEY_ERROR.getMessage());
            constructMsg(result, SignCode.ACCESSKEY_ERROR);
            return false;
        }
    }


    public static String getIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress().getAddress().getHostAddress();
        }
        return ip;
    }


    /**
     * 统一响应
     *
     * @param message
     * @param response
     * @param httpStatus
     * @return
     */
    public static Mono<Void> response(Map<String, Object> message, ServerHttpResponse response, HttpStatus httpStatus, String uuid, LocalDateTime now, String address, String method, String queryString, String body, String path, String account, String accessKeyId) {
        // 转换响应消息内容对象为字节
        byte[] bits = JsonUtils.toJSONString(message).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        // 设置响应对象状态码
        response.setStatusCode(httpStatus);
        // 设置响应对象内容并且指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        response.getHeaders().add("Request-id", uuid);
        ResponseLinkUtil.print(message, response.getStatusCode().value() + "", uuid, now, address, method, queryString, body, path, account, accessKeyId);
        // 返回响应对象
        return response.writeWith(Mono.just(buffer));
    }
}
