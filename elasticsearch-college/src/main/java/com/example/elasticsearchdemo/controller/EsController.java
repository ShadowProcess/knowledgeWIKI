package com.example.elasticsearchdemo.controller;


import com.example.elasticsearchdemo.dao.UserDocumentDao;
import com.example.elasticsearchdemo.document.UserDocument;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EsController {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Resource
    private UserDocumentDao userDocumentDao;

    @GetMapping(value = "create")
    public void createIndex(){
        elasticsearchRestTemplate.indexOps(UserDocument.class).create();
        UserDocument user = new UserDocument();
        user.setId("123456");
        user.setUserName("test");
        UserDocument saveUser = userDocumentDao.save(user);
        Assert.notNull(saveUser);
        System.out.println(userDocumentDao.findById(user.getId()));
        elasticsearchRestTemplate.indexOps(UserDocument.class).delete();

    }


}
