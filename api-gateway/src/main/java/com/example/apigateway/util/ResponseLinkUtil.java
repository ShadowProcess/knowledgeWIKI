package com.example.apigateway.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

//@Slf4j
@Slf4j
public class ResponseLinkUtil {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss:SSS");

    public static void print(Map<String, Object> message, String httpStatus, String uuid, LocalDateTime now, String address, String method, String queryString, String body, String path, String account, String accessKeyId) {
        log.info("{}  {}  {}  {}  {}  {}  {}  {}  {}  {}  {}  {}  {}", now.format(formatter), LocalDateTime.now().format(formatter), uuid, account, accessKeyId, address, path, method, queryString, httpStatus, "\"" + message.get("msg") + "\"", "gate-way", "-");
    }
}
