package com.example.post;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * 使用Apache框架 HttpClient
 * HTTP Post   StringEntity
 */
public class HttpClientPostDemo {

    public static void main(String[] args) throws Exception {
        //全局配置
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(5000)  //读取的超时时间 //5.0版本已不存在
                .setConnectTimeout(5000) //网络请求的超时时间
                .setConnectionRequestTimeout(5000) //连接池去获取连接的超时时间
                .setProxy(new HttpHost("10.4.13.149", 7128)) //可以使用代理
                .build();


        CloseableHttpClient aDefault = createSSLInsecureClient();

        HttpPost httpPost = new HttpPost("https://open.e.189.cn/auth/h5codeinfo.do");
        httpPost.setConfig(config);

        //***设置Post参数
        /**
         * 普通表单使用（UrlEncodeFormEntity）
         * json可使用（StringEntity）
         */
        JSONObject param = new JSONObject();
        param.put("name","java");
        param.put("pass","12334");

        StringEntity stringEntity = new StringEntity(param.toString());

        httpPost.setHeader("content-type","json");
        httpPost.setEntity(stringEntity);

        CloseableHttpResponse response = aDefault.execute(httpPost);


        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    //可用
    //信任所有证书
    public static CloseableHttpClient createSSLInsecureClient() throws Exception {
        HostnameVerifier hostnameVerifier = (hostname, session) -> true;
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                .build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
        return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
    }
}
