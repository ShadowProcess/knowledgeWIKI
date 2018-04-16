package com.example.webfluxdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * WebFlux 应用中，所有数据都应该以Mono、Flux的形式表示，这样才能带来最好的性能和高吞吐量。
 * Mono和Flux 这两种数据模型是WebFlux的核心，本片入门教程只是简单的展示了WebFlux项目的搭建编写，
 * 要学好WebFlux 还需要掌握Reactor(Mono、Flux)，其实Reactor 和 Stream 的很多操作都有类似功能，
 * 如果你对Java8Stream非常熟悉的话，学习Reactor应该是也是件容易的事情，如果还不掌握Stream 建议先去学好Stream。
 *
 * Mono：实现发布者，并返回 0 或 1 个元素
 * Flux：实现发布者，并返回 N 个元素
 *
 *
 * Spring Webflux
 *
 * Spring Boot Webflux 就是基于 Reactor 实现的。Spring Boot 2.0 包括一个新的 spring-webflux 模块。
 * 该模块包含对响应式 HTTP 和 WebSocket 客户端的支持，以及对 REST，HTML 和 WebSocket 交互等程序的支持。
 * 一般来说，Spring MVC 用于同步处理，Spring Webflux 用于异步处理。
 *
 存储：Redis、MongoDB、Cassandra。不支持 MySQL
 内嵌容器：Tomcat、Jetty、Undertow

 */

@Slf4j
@RestController
public class WebFluxController {


    //这种执行模式是同步非阻塞的,也就是Mono产生数据，和Mono与Http 之间的订阅消费都是在同一个线程。
    @GetMapping("mono")
    public Mono<String> mono() {
        return Mono.just("hello webflux");
    }

    @GetMapping("mono1")
    public Mono<Object> mono1() {
        return Mono.create(monoSink -> {
            log.info("创建 Mono");
            monoSink.success("hello webflux");
        })
                .doOnSubscribe(subscription -> { //当订阅者去订阅发布者的时候，该方法会调用
                    log.info("{}", subscription);
                }).doOnNext(o -> { //当订阅者收到数据时，改方法会调用
                    log.info("{}", o);
                });
    }


    @GetMapping("flux")
    public Flux<String> flux() {
        return Flux.just("hello","webflux","spring","boot");
    }

}
