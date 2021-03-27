package com.example.snapphoto.controller;

import com.example.snapphoto.service.OrderSnapShootService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// phantomjs /u/script/screenshot.js "https://l.sh.189.cn/preWeakLogin?callbackUrl=/index" /tmp/ill3.jpg
// phantomjs /u/script/screenshot.js "https://www.baidu.com/?tn=49055317_11_hao_pg" /tmp/baidu.jpg

@Slf4j
@Controller
public class OrderSnapShootController {

    @Autowired
    private OrderSnapShootService orderSnapShootService;

    @GetMapping("/web/camera")
    @ResponseBody
    public String camera(String uri) {
        log.info("快照地址:{}", uri);
        String jsLocated = "/u/script/screenshot.js";
        val imgDir = orderSnapShootService.getImgDir();
        log.info("图片地址:{}",imgDir);
        orderSnapShootService.exec(jsLocated, uri, imgDir);
        return "ok";
    }
}
