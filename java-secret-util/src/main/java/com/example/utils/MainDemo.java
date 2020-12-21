package com.example.utils;

import com.example.utils.cipher.RequestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

//import com.example.demo.src.utils.HttpClientUtil;
//import com.example.demo.src.utils.HttpClientUtil;


public class MainDemo {

    private static final Logger logger = null;

    // 预授权接口（HTTP 三网）URL
     private static final String PRE_GETMOBILE_URL = "http://id6.me/openapi/networkauth/preGetMobile.do";

    public static void main(String [] args) {

        String timeStamp = String.valueOf(System.currentTimeMillis());
        String format = "json";
        // 公共请求参数
        Map<String, String> generalParamsMap = new HashMap<>();
        // 非公共请求参数
        Map<String, String> priveParamsMap = new HashMap<>();

        // 添加非公共请求参数
        // 时间戳
        priveParamsMap.put("timeStamp", timeStamp);
        // 业务类型
        priveParamsMap.put("bussinessType", "jy");

        logger.info("main() - begin - encrypt paramStr by AES");
        logger.info("main() - before AES encryption - paramStr = " + RequestUtil.getParamString(priveParamsMap));
        // 不公开请求参数密文
        String paramStr= RequestUtil.getParamStr(priveParamsMap, Constants.APP_AES_KEY);
        logger.info("main() - after AES encryption - paramStr = " + paramStr);
        logger.info("main() - end - encrypt paramStr by AES");

        logger.info("main() - begin - encrypt paramKey by RSA");
        logger.info("main() - before RSA encryption - paramKey = " + Constants.APP_AES_KEY);
        // 秘钥A的密文
        String paramKey= RequestUtil.getParamKey(Constants.TELETE_COM_PUBLIC_KEY, Constants.APP_AES_KEY);
        logger.info("main() - after RSA encryption - paramKey = " + paramKey);
        logger.info("main() - end - encrypt paramKey by RSA");

        // 添加公共请求参数
        // 接入方 Id
        generalParamsMap.put("clientId", Constants.APP_ID);
        // 返回格式
        generalParamsMap.put("format",format);
        // 客户端类型
        generalParamsMap.put("clientType","H5-20200");
        // 密钥A的密文
        generalParamsMap.put("paramKey",paramKey);
        // 不公开请求参数密文
        generalParamsMap.put("paramStr",paramStr);
        // 接口版本
        generalParamsMap.put("version","1.5");

        logger.info("main() - begin - sign signature by HmacSha1");
        logger.info("main() - before signature - sign = " + RequestUtil.generatePlainText(generalParamsMap));
        // 接口签名
        String sign = RequestUtil.getSignString(generalParamsMap, Constants.APP_SECRET);
        logger.info("main() - after signature - sign = " + sign);
        logger.info("main() - end - sign signature by HmacSha1");
        generalParamsMap.put("sign",sign);

        logger.info("main() - request parameter - generalParamsMap = " + generalParamsMap);

        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String, String> entry : generalParamsMap.entrySet()){
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        logger.info("main() - request url - url = "+PRE_GETMOBILE_URL+"?"+sb.toString());

        // 执行post请求
        //String resp = HttpClientUtil.doPost(PRE_GETMOBILE_URL, generalParamsMap);
        //logger.info("main() - Telecom return data - resp = " + resp);
    }

}
