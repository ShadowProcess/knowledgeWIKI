package com.example.aliyundemo.controller;

import com.example.aliyundemo.jpa.entity.Book;
import com.example.aliyundemo.msgobject.BookPagination;
import com.example.aliyundemo.service.BookService;
import com.example.aliyundemo.service.SliderService;
import com.example.aliyundemo.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api-pc")
public class AController {

    @Autowired
    private HttpServletRequest request;
    @Autowired(required = false)
    private HttpServletResponse response;
    @Autowired
    private BookService bookService;
    @Autowired
    private SliderService sliderService;

    @GetMapping("test")
    public String get() throws UnsupportedEncodingException {
        System.out.println("request---" + request);
        System.out.println(request.getRequestURL());
        System.out.println(request.getMethod());
        System.out.println(request.getAuthType());
        System.out.println(request.getRemoteAddr());
        System.out.println(request.getSession().getId());

        String ipAddr = IPUtil.getIpAddr(request);
        System.out.println(ipAddr);


        log.info("中文乱码| 乱码原因:");
        /**
         * docker中的mysql默认又不是utf-8
         * 需要设置docker中的mysql编码
         */
        System.out.println(request.getServletPath()); //请求的路径 [/api/test]

        response.setHeader("Content-Type","text/html;charset=UTF-8");
        String name = "你乱码干什么";
        //byte[] utf8 = name.getBytes("UTF-8");
        //String s = new String(utf8,"UTF-8");
        //System.out.println(s);
        return name;
    }


    @GetMapping("/test111")
    @ResponseBody
    public String test111(@RequestParam(defaultValue = "-1", name = "id") Long id) {
        System.out.println(id);
        return "303 <a href=\"http://localhost:8081/api-pc/test111?flag=3&id=1\">W3School</a>";
    }


    @GetMapping("/sliders")
    public List<String> getSliders() {
        List<String> sliders = sliderService.getSliders();
        return sliders;
    }

    @GetMapping(value = "/findAll")
    public List<Book> findAll() {
        List<Book> all = bookService.findAll();
        return all;
    }

    @GetMapping(value = "/page")
    public BookPagination pagination(@RequestParam int offset) {
        List<Book> all = bookService.findPage2(offset, 5);
        BookPagination p = new BookPagination();
        if (CollectionUtils.isEmpty(all)) {
            p.setHasMore(false);
            p.setBooks(null);
            return p;
        }
        p.setHasMore(true);
        p.setBooks(all);
        return p;
    }

    @GetMapping(value = "/page2")
    public BookPagination findAll(@RequestParam int pageNum) {
        log.info("哈哈");
        Page<Book> all = bookService.findPage(pageNum);
        BookPagination p = new BookPagination();
        if (all == null || all.getSize() == 0) {
            p.setHasMore(false);
            p.setBooks(null);
            return p;
        }
        p.setHasMore(true);
        p.setBooks(all.getContent());
        return p;
    }

    @GetMapping(value = "/hot")
    public List<Book> getHotBook() {
        List<Book> all = bookService.findAll();
        Collections.reverse(all);
        return all;
    }


    @GetMapping(value = "/book")
    public Book getBook(@RequestParam Long bookId) {
        Book book = bookService.queryBook(bookId);
        return book;
    }

    @PostMapping(value = "/book")
    public boolean addBook(Book book) {
        boolean b = bookService.addBook(book);
        return b;
    }

    @PutMapping(value = "/book")
    public boolean editBook(@RequestParam Long id, Book book) {
        boolean b = bookService.editBook(id, book);
        return b;
    }

    @DeleteMapping(value = "/book")
    public boolean removeBook(@RequestParam Long bookId) {
        bookService.deleteBook(bookId);
        return true;
    }


    @GetMapping(value = "fxyVerify3")
    public boolean test1(@RequestParam Long bookId) {
        return true;
    }

    @GetMapping(value = "fxyVerify5")
    public boolean test2(@RequestParam Long bookId) {
        return true;
    }
}
