package com.example.apigateway.filter;

import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@Configuration
public class TestFilter implements GlobalFilter, Ordered {
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("过滤器执行");

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        System.out.println(request.getURI());
        HttpHeaders headers = request.getHeaders();
        headers.forEach((x, y) -> System.out.println(x + "---" + y));



        StringBuilder stringBuilder = EncodeParam(request.getQueryParams());
        String uriStr = request.getURI().toString();
        URI newUri = UriComponentsBuilder.fromUri(new URI(uriStr))
                .replaceQuery(stringBuilder.toString().replaceFirst("&", ""))
                .build()
                .toUri();
        ServerHttpRequest.Builder mutate = request.mutate();
        //添加参数
        mutate.uri(newUri);
        mutate.header("accessKey", "123456");
        request = mutate.build();
        ServerWebExchange serverWebExchange = exchange.mutate().response(response).build();
        ServerWebExchange build = serverWebExchange.mutate().request(request).build();


        //TODO 放行请求
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        System.out.println("获取执行序列号");
        return 1;
    }


    /**
     * 将每个参数值做encode
     *
     * @param queryParams
     * @return
     * @throws UnsupportedEncodingException
     */
    private StringBuilder EncodeParam(MultiValueMap<String, String> queryParams) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : queryParams.keySet()) {
            stringBuilder.append("&");
            stringBuilder.append(key).append("=").append(URLEncoder.encode(queryParams.get(key).get(0), "utf-8"));
        }
        return stringBuilder;
    }
}
