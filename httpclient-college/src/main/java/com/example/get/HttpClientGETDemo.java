package com.example.get;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *
 * 使用Apache框架 HttpClient
 * HTTP GET
 */
public class HttpClientGETDemo {

    public static void main(String[] args) throws IOException {
        //全局配置
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(5000)  //读取的超时时间 //5.0版本已不存在
                .setConnectTimeout(5000) //网络请求的超时时间
                .setConnectionRequestTimeout(5000) //连接池去获取连接的超时时间
                .setProxy(new HttpHost("10.4.13.149", 7128)) //可以使用代理
                .build();


        CloseableHttpClient aDefault = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        httpGet.setConfig(config);

        CloseableHttpResponse response = aDefault.execute(httpGet);


        System.out.println(response.getStatusLine().getStatusCode());
        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            System.out.println(header.getName());
            System.out.println(header.getValue());
        }
        HttpEntity entity = response.getEntity();
        System.out.println(entity.getContent());
        System.out.println(EntityUtils.toString(entity));
    }
}
