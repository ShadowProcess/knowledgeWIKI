package com.hello.transaction;

import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {


    @Override
    public void purchase(String username, String isbn) {
        //1.获取书的单价
        //2.更新书的库存
        //3.更新用户余额
    }
}
