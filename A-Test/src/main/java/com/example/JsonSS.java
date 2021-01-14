package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.junit.Test;

import java.util.List;

public class JsonSS {

    @Test
    public void test(){

        String s1 = "{\"code\":\"1\",\"dog\":{\"name\":\"al\"},\"desc\":\"3\"}";
        String s2 = "{\"code\":\"0\",\"data\":\"{\\\"hits\\\":{\\\"hits\\\":[{\\\"_source\\\":{\\\"lab307\\\":6499.0,\\\"lab146\\\":\\\"ACM-AXR\\\",\\\"lab286\\\":\\\"0\\\",\\\"lab295\\\":\\\"1\\\",\\\"lab294\\\":\\\"27\\\"}}],\\\"total\\\":1},\\\"timed_out\\\":false}\",\"desc\":\"ok\"}";
        OneResultParam oneResultParam = JSON.parseObject(s2, OneResultParam.class);
        System.out.println(oneResultParam.data);

        BaseBean baseBean = JSON.parseObject(oneResultParam.getData(), BaseBean.class);
        System.out.println(baseBean);
    }
}



@Data
class OneResultParam{
    String code;
    String data;
    String desc;
}

@Data
class BaseBean{
    @JSONField(name = "hits") //使用阿里巴巴的这个注解，那么就要和上面的JSON.parseObject进行配套使用
    private HitsFirst hitsFirst;
    @JSONField(name = "timed_out")
    private boolean timedOut;
}

@Data
class HitsFirst{
    @JSONField(name = "hits")
    private List<HitsSecond> hitsSeconds;
    private int total;
}

@Data
class HitsSecond{
    @JSONField(name = "_source")
    private Source source;
}

@Data
class Source{
    private int lab307;
    private String lab146;
    private String lab286;
    private String lab295;
    private String lab294;
}