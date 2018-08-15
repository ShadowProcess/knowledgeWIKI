package com.example.basic.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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


@Service
public class WebURIService implements ApplicationContextAware {

    ApplicationContext ctx;
    private Set<String> urlPatternsSet;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }


    @PostConstruct
    private void init() {
        Set<String> urlSet = new HashSet<>();
        RequestMappingHandlerMapping bean = ctx.getBean(RequestMappingHandlerMapping.class);//从容器中拿到处理器映射器
        //Map<映射路径,对应的处理类的那个方法>
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            PatternsRequestCondition pc = rmi.getPatternsCondition();
            Set<String> pSet = pc.getPatterns();
            urlSet.addAll(pSet);
        }
        this.urlPatternsSet = Collections.unmodifiableSet(urlSet);
    }
}
