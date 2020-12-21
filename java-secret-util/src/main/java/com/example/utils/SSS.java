package com.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.Objects;


@Slf4j
public class SSS {


    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://open.e.189.cn/auth/h5codeinfo.do?appId=8237729420&timeStamp=1594289871596&format=json&params=47d44b9b205033371320d9977625b7fe994f49426be8c95bb3fc1c08305e60a70dc2c12f2218564985b5a379484c0ffae5b6f093&accessCode=nmd1ec524fba514e9a8297d856f01bd889&sign=91F6E1440AFAEFB82FAF142583AD15AABAC7CCA4BBCAC4B94637742AB5BC81931DD79F784C21484DCACB16A4FB08A4B0F3E21BF0FEA8D3C107137D11736AD015A6F2BD266DAAA09F2A679F899FD00464AC92DABF6232C1C23B6095F7858E54A051A8AC779EC7848A43B3D2E21EDA6FDCA98164C2AED35F79D2482B6B34247ACC");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        try {
            //发起请求
            log.info("开始发送请求 --->");
            HttpResponse response = httpClient.execute(httpPost);
            log.info("收到服务器响应:{}", response);
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            log.error("请求授权服务器错误->{}", e.getStackTrace());
        } finally {
            if (Objects.nonNull(httpClient)) {
                try {
                    httpClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

