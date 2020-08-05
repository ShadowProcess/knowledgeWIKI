package com.example.controller.redirect_attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RedirectAttributesController {

    @Autowired
    private HttpServletRequest request;


    /**
     * 重定向两种添加参数的方式：
     * <p>
     * 第一种：
     * redirectAttributes.addAttribute("param1",value1); 这种方法相当于在重定向链接地址追加传递的参数
     * <p>
     * 以上重定向的方法等同于 return:"redirect:/path/list?param1=value1
     * 注意这种方法直接将传递的参数暴露在链接地址上，非常的不安全，慎用。
     */
    @GetMapping(value = "111")
    public String index(RedirectAttributes redirect) {
        redirect.addAttribute("callbackUrl", request.getServletPath());
        return "redirect:/index1";
    }

    @GetMapping(value = "index1")
    @ResponseBody
    public String s(String callbackUrl) {
        return callbackUrl;
    }





//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 第二种：
     * redirectAttributes.addFlashAttribute("param",value); 这种方法是隐藏了参数，链接地址上不直接暴露
     *
     * 二者区别：
     * addFlashAttribute（）实际上将属性存储在Flashmap中 （该属性在用户会话中内部维护，并在下一个重定向请求得到满足时被删除）
     * addAttribute（）本质上是根据您的属性构造请求参数，并使用请求参数重定向到所需的页面。
     *
     * 因此addFlashAttribute（）的优点是您可以在Flash属性中存储几乎任何对象（因为它根本没有序列化到请求参数中，而是作为对象维护），
     * 而使用addAttribute（）则是因为您将对象如果将add转换为普通的请求参数，则您只能使用String之类的对象类型。
     *
     * 获取值的三种方式：
     * 方式1：ModelMap
     * 方式2：ModelAttribute
     * 方式3：RequestContextUtils.getInputFlashMap(request).get("key")
     * 三种方式都可以获取到RedirectAttributes设置的值
     */
    @GetMapping(value = "222")
    public String index1(RedirectAttributes redirect) {
        redirect.addFlashAttribute("callbackUrl", request.getServletPath());
        return "redirect:/index2";
    }

    @GetMapping(value = "index2")
    @ResponseBody
    public String s1(@ModelAttribute(value = "callbackUrl") String callbackUrl) {
        Object callbackUrl1 = RequestContextUtils.getInputFlashMap(request).get("callbackUrl");
        System.out.println(callbackUrl1);
        return callbackUrl;
    }


}
