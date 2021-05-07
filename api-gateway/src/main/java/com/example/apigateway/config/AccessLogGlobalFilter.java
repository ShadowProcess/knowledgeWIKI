//package com.example.apigateway.config;
//
//import com.asia.gate.log.ResponseLinkUtil;
//import com.asia.gate.util.*;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.reactivestreams.Publisher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.core.io.buffer.DefaultDataBufferFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//import java.nio.charset.Charset;
//import java.time.LocalDateTime;
//import java.util.Map;
//import java.util.UUID;
//
//
//@Slf4j
//@Component
//public class AccessLogGlobalFilter implements GlobalFilter, Ordered {
//
//
//    @Autowired
//    RequestBody requestBody;
//    @Autowired
//    RedisUtil redisUtil;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        LocalDateTime now = LocalDateTime.now();
//        ServerHttpRequest request = exchange.getRequest();
//
//        //获取请求的ip地址
//        String hostString = AuthUtil.getIpAddress(request);
//        URI uri = request.getURI();
//        // 从请求中获取 token 参数
//        String methodValue = request.getMethodValue();
//
//        //获取body
//        String reqBody = "";
//        if (methodValue.toLowerCase().equals("post") || methodValue.toLowerCase().equals("put")) {
//            reqBody = requestBody.resolveBodyFromRequest(request);
//        }
//
//        String query = uri.getQuery();
//
//        String uuid = Random();
//        ServerHttpRequest.Builder mutate = request.mutate();
//        mutate.header("Request-id", uuid);
//        request = mutate.build();
//        ServerHttpResponse response = exchange.getResponse();
//        DataBufferFactory bufferFactory = response.bufferFactory();
//        ServerHttpRequest finalRequest = request;
//        String finalReqBody = reqBody;
//        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
//            @Override
//            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                if (body instanceof Flux) {
//                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);
//                    return super.writeWith((fluxBody.buffer().map(dataBuffer -> {
//                        // probably should reuse buffers
//                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
//                        DataBuffer join = dataBufferFactory.join(dataBuffer);
//                        byte[] content = new byte[join.readableByteCount()];
//                        join.read(content);
//                        DataBufferUtils.release(join);
//                        String responseData = new String(content, Charset.forName("UTF-8"));
//                        Map<String, Object> stringObjectMap = JsonUtils.toMap(responseData);
//                        HttpHeaders headers = finalRequest.getHeaders();
//                        String accessKeyId = headers.get("accessKeyId").get(0);
//                        Map<Object, Object> objectMap = redisUtil.hgetMore("user:secret:" + accessKeyId);
//                        String account = objectMap.get("account").toString();
//                        ResponseLinkUtil.print(stringObjectMap, response.getStatusCode().value() + "", uuid, now, hostString, methodValue, query, finalReqBody, uri.getPath(), account, accessKeyId);
//                        return bufferFactory.wrap(content);
//                    })));
//                }
//                return super.writeWith(body); // if body is not a flux. never got there.
//            }
//        };
//        return chain.filter(exchange.mutate().request(request).response(decoratedResponse).build());
//    }
//
//    @Override
//    public int getOrder() {
//        return HIGHEST_PRECEDENCE + 100;
//    }
//
//    /**
//     * 生成随机八位字符串
//     *
//     * @return
//     */
//    public String Random() {
//        UUID uuid = UUID.randomUUID();
//        String encrypt = EncryptUtil.encrypt(uuid.toString());
//        String random = RandomStringUtils.random(8, encrypt);
//        return random;
//    }
//}
