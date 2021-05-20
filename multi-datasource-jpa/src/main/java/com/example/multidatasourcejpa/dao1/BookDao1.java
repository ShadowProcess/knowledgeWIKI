package com.example.multidatasourcejpa.dao1;

import com.example.multidatasourcejpa.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao1 extends JpaRepository<Book,Integer> {
}

