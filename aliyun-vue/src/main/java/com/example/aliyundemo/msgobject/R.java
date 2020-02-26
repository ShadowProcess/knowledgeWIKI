package com.example.aliyundemo.msgobject;


import java.io.Serializable;

public class R<T> implements Serializable {

    protected String code;
    protected String message;
    protected String token;
    protected T data;

    public R(String code, String message, String token, T data) {
        this.code = code;
        this.message = message;
        this.token = token;
        this.data = data;
    }

    public static <T> R<T> ok100() {
        return new R<>("100", null, null, null);
    }

    public static <T> R<T> ok(String code) {
        return new R<>(code, null, null, null);
    }

    public static <T> R<T> ok(String code, String message) {
        return new R<>(code, message, null, null);
    }

    public static <T> R<T> ok(String code, String message, String token) {
        return new R<>(code, message, token, null);
    }

    public static <T> R<T> ok(String code, String message, String token, T data) {
        return new R<>(code, message, token, data);
    }

    public static <T> R<T> okData(T data) {
        return new R<>(null, null, null, data);
    }


    @Override
    public String toString() {
        return "R{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", token='" + token + '\'' +
                ", data=" + data +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
