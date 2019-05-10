package com.example.thymeleafdemo.component;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

//给容器中添加自己定义的ErrorAttributes
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {


    //返回值map就是页面能获取的所有字段
    //使用post调用 不存在接口时 返回属性将增加 errorAttributes.put("company","禹州公司");
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("company","禹州公司");
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        errorAttributes.putAll(ext);
        return errorAttributes;
    }


}
