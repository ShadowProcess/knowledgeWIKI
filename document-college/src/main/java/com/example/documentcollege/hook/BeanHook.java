package com.example.documentcollege.hook;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Getter
@Component
@RequiredArgsConstructor
public class BeanHook {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private static BeanHook SELF;

    @Autowired
    public void setSelf(BeanHook self) {
        SELF = self;
    }

    public static BeanHook self(){
        return SELF;
    }
}
