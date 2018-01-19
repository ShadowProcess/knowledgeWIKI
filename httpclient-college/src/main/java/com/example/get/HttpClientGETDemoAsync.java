package com.example.get;


import java.io.IOException;

/**
 * 5.0版本支持异步
 */
public class HttpClientGETDemoAsync {


   // public static void main(String[] args) throws IOException {
//
   //     CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
   //     SimpleHttpRequest request = SimpleHttpRequest.get("http://www.baidu.com");
//
   //     Future<SimpleHttpRequest> future = client.execute(request,null);
//
   //     //得到响应结果
   //     SimpleHttpResponse simpleHttpResponse = future.get();
//
   //     simpleHttpResponse.getBodyText();
   //     SimpleBody body = simpleHttpResponse.getBody();
   //     System.out.println(body.getBodyText());
   // }
}
