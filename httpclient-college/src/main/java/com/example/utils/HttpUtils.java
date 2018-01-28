package com.example.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * HttpClient连接池的使用
 */

public class HttpUtils {

    //创建连接池管理对象
    static PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

    //全局配置
    static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(5000)  //读取的超时时间 //5.0版本已不存在
            .setConnectTimeout(5000) //网络请求的超时时间
            .setConnectionRequestTimeout(5000) //连接池去获取连接的超时时间
            .build();

    static {
        //配置最大连接数
        manager.setMaxTotal(300);
        //每个路由最大连接数，路由是根据host来管理的，所以这里的数量不太容易把握
        manager.setDefaultMaxPerRoute(20);
    }

    /**
     * 定义获取连接对象，从连接池中获取
     *
     * @return
     */
    public static CloseableHttpClient getHttpConnection() {
        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(manager)
                .setDefaultRequestConfig(requestConfig)
                .build();
        return client;
    }

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
