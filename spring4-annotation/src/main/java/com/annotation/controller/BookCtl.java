package com.annotation.controller;

import com.annotation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookCtl {

    @Autowired
    private BookService bookService;
}
