package com.example.multidatasourcejpa;

import com.example.multidatasourcejpa.bean.Book;
import com.example.multidatasourcejpa.dao1.BookDao1;
import com.example.multidatasourcejpa.dao2.BookDao2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MultiDatasourceJpaApplicationTests {

    @Autowired
    BookDao1 bookDao1;
    @Autowired
    BookDao2 bookDao2;

    @Test
    public void contextLoads() {
        List<Book> all = bookDao1.findAll();
        System.out.println(all);
        List<Book> all1 = bookDao2.findAll();
        System.out.println(all1);
    }
}
