package com.example.post;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 使用Apache框架 HttpClient
 * HTTP Post   StringEntity
 */
public class HttpClientPostDemo2 {

    public static void main(String[] args) throws IOException {
        //全局配置
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(5000)  //读取的超时时间 //5.0版本已不存在
                .setConnectTimeout(5000) //网络请求的超时时间
                .setConnectionRequestTimeout(5000) //连接池去获取连接的超时时间
                .setProxy(new HttpHost("10.4.13.149", 7128)) //可以使用代理
                .build();

        CloseableHttpClient aDefault = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://open.e.189.cn/auth/h5codeinfo.do");
        httpPost.setConfig(config);

        //***设置Post参数
        /**
         * 普通表单使用（UrlEncodeFormEntity）
         * json可使用（StringEntity）
         */
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("name","22"));
        pairs.add(new BasicNameValuePair("pass","22"));

        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs);

        httpPost.setHeader("content-type","json");
        httpPost.setEntity(urlEncodedFormEntity);

        CloseableHttpResponse response = null;
        try {
            response = aDefault.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
