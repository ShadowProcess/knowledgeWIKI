package com.annotation.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    IndexService indexService;

    public UserService(){
        System.out.println("construct from B");
    }

    public IndexService getIndexService() {
        return indexService;
    }
}
