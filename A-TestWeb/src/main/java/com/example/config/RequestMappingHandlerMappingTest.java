package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class RequestMappingHandlerMappingTest implements ApplicationContextAware {

    ApplicationContext ctx;
    private Set<String> urlPatternsSet; //该项目的所有接口

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @PostConstruct
    public void init() {
        log.info("初始化组件");
        Set<String> urlSet = new HashSet<>();
        RequestMappingHandlerMapping bean = ctx.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        log.info("所有接口具体描述:{}", handlerMethods.toString());
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            PatternsRequestCondition pc = rmi.getPatternsCondition();
            Set<String> pSet = pc.getPatterns();
            log.info("就有那些接口:{}", pSet.toString());
            urlSet.addAll(pSet);
        }
        this.urlPatternsSet = Collections.unmodifiableSet(urlSet);
    }

}
