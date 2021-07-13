package com.example.documentcollege.hook;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@UtilityClass
public class CtlUtils {

    public void setAttribute(String key, Object value) {
        BeanHook.self()
                .getRequest()
                .getSession(true)
                .setAttribute(key, value);
    }


    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String key) {
        val value = BeanHook.self()
                .getRequest()
                .getSession(true)
                .getAttribute(key);
        if (Objects.nonNull(value)) return (T) value;
        return null;
    }

    public void removeAttribute(String key) {
        BeanHook.self()
                .getRequest()
                .getSession(true)
                .removeAttribute(key);
    }

    public String getAttributeString(String key) {
        String obj = getAttribute(key);
        return (obj == null) ? "" : obj;
    }

    public HttpSession session() {
        return BeanHook.self()
                .getRequest()
                .getSession(true);
    }

    public HttpSession session(boolean createSession) {
        return BeanHook.self()
                .getRequest()
                .getSession(createSession);
    }
}
