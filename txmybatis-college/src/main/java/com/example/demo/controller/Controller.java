package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.Discarditem;
import com.example.demo.model.Version;
import com.example.demo.service.DiscardItemService;
import com.example.demo.service.VersionService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Alex
 */
@Slf4j
@RestController
public class Controller {

    private final VersionService versionService;
    private final DiscardItemService discardItemService;

    /**
     * 推荐构造方法注入
     * @param discardItemService
     * @param versionService
     */
    @Autowired
    public Controller(DiscardItemService discardItemService,VersionService versionService) {
        this.versionService = versionService;
        this.discardItemService = discardItemService;
    }

    @GetMapping(value = "/hello")
    public String test() {
        log.info("测试日志框架");
        PageInfo<Discarditem> page = discardItemService.page(1, 2);
        System.out.println("该页有几行:"+page.getPageSize());
        return JSONObject.toJSON(page).toString();
    }


    @GetMapping(value = "/save")
    public String save(){
        Version version = new Version();
        version.setStoreid("100009");
        version.setPosno(20);
        version.setVersion("1234");
        version.setMasterversion("2019");
        version.setUpdatedate("2019-08-09");
        versionService.save(version);
        return "ok";
    }


    @GetMapping(value = "/test")
    public String testCustomMapper(){
        Version test = versionService.test(1);
        return JSONObject.toJSON(test).toString();
    }
}
