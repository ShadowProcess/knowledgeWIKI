package com.example.get;

import com.example.utils.HttpUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 *
 * 使用Apache框架 HttpClient
 * HTTP GET
 */
public class HttpClientGETDemo2 {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpConnection = HttpUtils.getHttpConnection();

        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse execute = httpConnection.execute(httpGet);

        String s = EntityUtils.toString(execute.getEntity());
        System.out.println(s);
    }
}
