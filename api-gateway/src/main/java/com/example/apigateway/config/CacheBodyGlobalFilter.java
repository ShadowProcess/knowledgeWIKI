//package com.example.apigateway.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
///**
// * 缓存body
// */
//@Slf4j
//@Configuration
//public class CacheBodyGlobalFilter implements Ordered, GlobalFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String methodValue = exchange.getRequest().getMethodValue();
//        if ("POST".equals(methodValue) || "PUT".equals(methodValue)) {
//            return DataBufferUtils.join(exchange.getRequest().getBody())
//                    .flatMap(dataBuffer -> {
//                        DataBufferUtils.retain(dataBuffer);
//                        Flux<DataBuffer> cachedFlux = Flux
//                                .defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
//                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
//                                exchange.getRequest()) {
//                            @Override
//                            public Flux<DataBuffer> getBody() {
//                                return cachedFlux;
//                            }
//                        };
//                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
//                    });
//        }
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return Ordered.HIGHEST_PRECEDENCE;
//    }
//
//}
