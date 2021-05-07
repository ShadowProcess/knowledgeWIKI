package com.example.apigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }


    @Value("${api.http.addr}")
    private String netAddr;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        log.info("路由跳转地址：{}", netAddr);
        return builder
                .routes()
                .route("/xxx", r -> r.path("/openapi/v3/stat").uri(netAddr))
                /**
                 * route的id就是给路由起个名字，没什么作用
                 * 当访问： http://localhost:9000/openapi/v3/stat
                 * 将其路由到uri指定的网路地址
                 */
                .build();
    }
}
