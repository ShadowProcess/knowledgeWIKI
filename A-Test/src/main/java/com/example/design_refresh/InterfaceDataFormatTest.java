package com.example.design_refresh;

import lombok.Data;

import java.util.*;

public class InterfaceDataFormatTest {

    public static void main(String[] args) {
        PushTask publishTask = new PushTask();
        publishTask.setActionId(1000L);
        publishTask.setType("url");
        publishTask.setProvider("tencent");
        Set set1 = new HashSet();
        set1.add("http://vc.repair.dl.woniu.com/1test.txt");
        set1.add("http://vc.repair.dl.woniu.com/2test.txt");
        set1.add("http://vc.repair.dl.woniu.com/3test.txt");
        publishTask.setUrls(set1);

        System.out.println(publishTask);
        List<PushTask> publishTaskList;
    }
}

//推送任务接口接收数据格式
@Data
class PushTask {
    Long actionId;   //主任务id
    String provider; //厂商标识
    String type;     //要推送的类型dir or url
    Set<String> urls;//本次推送的URLS
}

//查询任务接口接收数据格式
@Data
class QueryTask {
    Long actionId;      //主任务id
    String provider;    //厂商标识
    List<Long> taskIds; //要查询的任务id
}

//推送查询接口,向刷新中间件响应信息格式
@Data
class R<T> {
    String code;    //响应状态码
    String message; //描述信息
    T data;         //响应数据
}


//错误消息进入队列消息格式
@Data
class ErrorMessage {
    String code;            //消息标识  1.推送失败 2.推送接口不通 3.查询失败 4.查询接口不通
    Long actionId;          //主任务id
    String provider;        //厂商标识
    String taskId;          //任务id (推送失败时，没有任务id) (查询失败有任务id)
    List<String> errorUrls; //错误urls
}


//邮件格式:

//▶格式1《厂商推送接口无响应》：
//系统出现厂商推送接口无法连接状况,请您查收邮件，并处理
//主任务id是:  XXX
//厂商是:  XXX
//本次推送无响应的URL有:  XXX
//请您处理后，在平台录入处理信息!
//
//▶格式2《厂商推送接口返回失败》：
//系统出现厂商返回推送失败状况,请您查收邮件，并处理
//主任务id是:  XXX
//厂商是:  XXX
//本次推送失败的URL有:  XXX
//请您处理后，在平台录入处理信息!
//
//
//▶格式3《厂商查询接口无响应》：
//系统出现厂商查询接口无法连接状况,请您查收邮件，并处理
//主任务id是:  XXX
//厂商是:  XXX
//本次查询无响应的URL有:  XXX
//请您处理后，在平台录入处理信息!
//
//
//▶格式4《厂商查询接口返回失败》：
//系统出现厂商查询接口返回失败状况,请您查收邮件，并处理
//主任务id是:  XXX
//厂商是:  XXX
//本次查询接口返回失败的URL有:  XXX
//请您处理后，在平台录入处理信息!


