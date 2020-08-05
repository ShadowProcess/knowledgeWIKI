//package com.example.httpclient_new;
//
//import jdk.incubator.http.HttpClient;
//import jdk.incubator.http.HttpRequest;
//import jdk.incubator.http.HttpResponse;
//
//import java.io.IOException;
//import java.net.URI;
//
///**
// * JDK9 HttpClient
// * 支持HTTP2
// * 支持WebSocket
// */
//public class HttpClientTest {
//
//    public static void main(String[] args) {
//        //jdk 9 中 使用 HttpClient替换原有的HttpURLConnection
//        try {
//            HttpClient client = HttpClient.newHttpClient();
//
//            HttpRequest req = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).GET().build();
//
//            HttpResponse<String> response = null;
//            response = client.send(req, HttpResponse.BodyHandler.asString());
//
//            System.out.println(response.statusCode());    //200
//            System.out.println(response.version().name());//HTTP_1_1
//            System.out.println(response.body());          //响应的HTML
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
