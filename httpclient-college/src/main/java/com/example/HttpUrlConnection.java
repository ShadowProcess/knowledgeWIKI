package com.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * java原生方式--发送HTTP请求
 */
public class HttpUrlConnection {

    public static void main(String[] args) throws Exception {
        HttpURLConnection urlConn = null;
        URL url = new URL("http://www.baidu.com");
        urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setRequestMethod("GET");
        urlConn.setConnectTimeout(5000);
        urlConn.setReadTimeout(5000);
        urlConn.setUseCaches(false);
        urlConn.connect();

        InputStream inputStream = urlConn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader buff = new BufferedReader(inputStreamReader);
        String line = buff.readLine();
        while (line!=null){
            System.out.println(line);
            line = buff.readLine();
        }
        inputStream.close();
        inputStreamReader.close();
        buff.close();
    }



}
