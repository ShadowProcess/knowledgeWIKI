package com.annotation.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    //@Transactional(transactionManager = "事务管理器名字") 一般我们都不指定
    @Transactional
    public void insertUser(){
        userDao.insert();
        //otherDao.other(); xxx
        System.out.println("插入完成...");
        //int i = 10/0;
        int i = 10/0;
    }

}
