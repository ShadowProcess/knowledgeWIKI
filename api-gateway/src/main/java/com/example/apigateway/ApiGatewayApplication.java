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


    @Value("${api.http.addr1}")
    private String netAddr1;
    @Value("${api.http.addr2}")
    private String netAddr2;
    @Value("${api.http.addr3}")
    private String netAddr3;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("xxx1",
                        r -> r.path("/openapi/v1/**").uri(netAddr1))
                /**
                 * #路由的ID，没有固定规则但要求唯一，简易配合服务
                 * 当访问： http://localhost:9000/openapi/v3/stat，将其路由到uri指定的网路地址netAddr
                 */
                .route("xxx2",
                        r -> r.path("/openapi/v2/**").uri(netAddr2))
                .route("xxx3",
                        r -> r.path("/openapi/v3/**").uri(netAddr3))
                .build();
    }
}
