package com.example.multithread.jpa.support;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Getter
@Component
public class BeanHook implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Autowired
    private EntityManager entityManager;

    public static BeanHook self() {
        return applicationContext.getBean(BeanHook.class);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        BeanHook.applicationContext = applicationContext;
    }
}
