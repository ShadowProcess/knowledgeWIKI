package com.service;

import com.annotation.BookInfo;
import com.domain.User;

public interface BookService {

    @BookInfo(value = "添加图书")
    public void addBook(User user) throws Exception;

    @BookInfo(value = "修改图书")
    public void updateBook(User user) throws Exception;

    @BookInfo(value = "删除图书")
    public void deleteBook(User user) throws Exception;

    @BookInfo(value = "查看图书")
    public void searchBook(User user) throws Exception;
}
