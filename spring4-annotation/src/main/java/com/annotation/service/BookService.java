package com.annotation.service;

import com.annotation.dao.BookDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BookService {

//    @Qualifier("bookDao") //@Qualifier注解表示明确的指定我要装配的组件id,而不是使用属性名
//    @Autowired
//    @Resource(name = "bookDao")
    @Inject
    private BookDao bookDao;

    public void print(){
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
