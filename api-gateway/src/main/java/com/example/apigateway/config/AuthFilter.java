//package com.example.apigateway.config;
//
//import com.asia.gate.enumeration.SignCode;
//import com.asia.gate.service.LimitService;
//import com.asia.gate.util.AuthUtil;
//import com.asia.gate.util.RedisUtil;
//import com.asia.gate.util.RequestBody;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.util.UriComponentsBuilder;
//import reactor.core.publisher.Mono;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URLEncoder;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Slf4j
//@Configuration
//public class AuthFilter implements GlobalFilter, Ordered {
//
//    @Autowired
//    RedisUtil redisUtil;
//    @Autowired
//    AuthUtil authUtil;
//    @Autowired
//    LimitService limitService;
//    @Autowired
//    RequestBody requestBody;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        LocalDateTime now = LocalDateTime.now();
//        //请求对象
//        ServerHttpRequest request = exchange.getRequest();
//        // 响应对象
//        ServerHttpResponse response = exchange.getResponse();
//        // 响应消息内容对象
//        Map<String, Object> message = new ConcurrentHashMap<>();
//        HttpHeaders headers = request.getHeaders();
//        String uuid = headers.get("Request-id").get(0);
//
//        //获取请求的ip地址
//        String hostString = AuthUtil.getIpAddress(request);
//        log.info("[{}],请求来源，{}", uuid, hostString);
//        URI uri = request.getURI();
//        String uriStr = uri.toString();
//        String path = uri.getPath();
//        log.info("[{}],请求url,{}", uuid, uriStr);
//        // 从请求中获取 token 参数
//        String methodValue = request.getMethodValue();
//
//        String query = uri.getQuery();
//
//
//        //获取body
//        String body = "";
//        if (methodValue.toLowerCase().equals("post") || methodValue.toLowerCase().equals("put")) {
//            body = requestBody.resolveBodyFromRequest(request);
//        }
//        response.getHeaders().add("Request-id", uuid);
//        //获取head
//        List<String> accessKeyIds = headers.get("accessKeyId");
//        List<String> nonces = headers.get("nonce");
//        List<String> timestamps = headers.get("timestamp");
//        List<String> signatures = headers.get("signature");
//
//        // 响应状态
//        message.put("code", "1");
//
//        message.put("data", "");
//        String account = "";
//        String accessKeyId = "";
//        try {
//            if (accessKeyIds != null && nonces != null && timestamps != null && signatures != null) {
//                accessKeyId = accessKeyIds.get(0);
//                String nonce = nonces.get(0);
//                String timestamp = timestamps.get(0);
//                String signature = signatures.get(0);
//                Map<Object, Object> objectMap = redisUtil.hgetMore("user:secret:" + accessKeyId);
//                if (objectMap != null && objectMap.size() > 0) {
//                    account = objectMap.get("account").toString();
//                    boolean checkParam = authUtil.checkParam(message, accessKeyId, nonce, timestamp, signature, response, request, objectMap, uuid, methodValue, body);
//                    if (!checkParam) {
//                        return AuthUtil.response(message, response, HttpStatus.UNAUTHORIZED, uuid, now, hostString, methodValue, query, body, path, account, accessKeyId);
//                    } else {
//                        boolean limit = limitService.limit(redisUtil, objectMap, accessKeyId, uuid, account);
//                        if (!limit) {
//                            log.info("[{}],{}", uuid, "请求达到上限");
//                            message.put("msg", "Too Many Requests");
//                            return AuthUtil.response(message, response, HttpStatus.TOO_MANY_REQUESTS, uuid, now, hostString, methodValue, query, body, path, account, accessKeyId);
//                        }
//                    }
//                } else {
//                    log.info("[{}],{}", uuid, SignCode.ACCESSKEY_ERROR.getMessage());
//                    message.put("code", SignCode.ACCESSKEY_ERROR.getCode());
//                    message.put("msg", SignCode.ACCESSKEY_ERROR.getMessage());
//                    return AuthUtil.response(message, response, HttpStatus.FORBIDDEN, uuid, now, hostString, methodValue, query, body, path, account, accessKeyId);
//                }
//            } else {
//                if (accessKeyIds == null) {
//                    log.info("[{}],{}", uuid, SignCode.ACCESSKEY_EMPTY.getMessage());
//                    constructMsg(message, SignCode.ACCESSKEY_EMPTY);
//                } else if (nonces == null) {
//                    log.info("[{}],{}", uuid, SignCode.NONCE_EMPTY.getMessage());
//                    constructMsg(message, SignCode.NONCE_EMPTY);
//                } else if (timestamps == null) {
//                    log.info("[{}],{}", uuid, SignCode.TIMESTAMP_EMPTY.getMessage());
//                    constructMsg(message, SignCode.TIMESTAMP_EMPTY);
//                } else if (signatures == null) {
//                    log.info("[{}],{}", uuid, SignCode.SIGNATURE_EMPTY.getMessage());
//                    constructMsg(message, SignCode.SIGNATURE_EMPTY);
//                }
//                // 响应内容
//                return AuthUtil.response(message, response, HttpStatus.UNAUTHORIZED, uuid, now, hostString, methodValue, query, body, path, account, accessKeyId);
//            }
//            StringBuilder stringBuilder = EncodeParam(request.getQueryParams());
//            stringBuilder.append("&account=").append(URLEncoder.encode(account, "utf-8"));
//            URI newUri = UriComponentsBuilder.fromUri(new URI(uriStr))
//                    .replaceQuery(stringBuilder.toString().replaceFirst("&", ""))
//                    .build()
//                    .toUri();
//            ServerHttpRequest.Builder mutate = request.mutate();
//            //添加参数
//            mutate.uri(newUri);
//            mutate.header("accessKey", accessKeyId);
//            request = mutate.build();
//            ServerWebExchange serverWebExchange = exchange.mutate().response(response).build();
//            ServerWebExchange build = serverWebExchange.mutate().request(request).build();
//            return chain.filter(build);
//        } catch (Exception e) {
//            log.error("网关错误", e);
//            message.put("msg", "Internal error");
//            return AuthUtil.response(message, response, HttpStatus.INTERNAL_SERVER_ERROR, uuid, now, hostString, methodValue, query, body, path, account, accessKeyId);
//        }
//    }
//
//
//    /**
//     * 将每个参数值做encode
//     *
//     * @param queryParams
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    private StringBuilder EncodeParam(MultiValueMap<String, String> queryParams) throws UnsupportedEncodingException {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String key : queryParams.keySet()) {
//            stringBuilder.append("&");
//            stringBuilder.append(key).append("=").append(URLEncoder.encode(queryParams.get(key).get(0), "utf-8"));
//        }
//        return stringBuilder;
//    }
//
//
//    @Override
//    public int getOrder() {
//        return -100;
//    }
//
//
//    private <T extends Map<String, Object>> T constructMsg(T rt, SignCode errorCode) {
//        rt.put("code", errorCode.getCode());
//        rt.put("msg", errorCode.getMessage());
//        return rt;
//    }
//}
