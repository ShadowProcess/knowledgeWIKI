package com.example.multidatasourcejpa.dao2;

import com.example.multidatasourcejpa.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao2 extends JpaRepository<Book,Integer> {
}
