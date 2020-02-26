package com.example.aliyundemo.jpa.repo;

import com.example.aliyundemo.jpa.entity.Book;
import com.example.aliyundemo.jpa.support.StandardRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends StandardRepository<Book,Long> {

    /**
     * 另外一种分页查询
     * @param offset
     * @param pageSize
     * @return
     */
    @Query(value = "select * from `book` limit ?1,?2",nativeQuery = true)
    List<Book> queryPage(int offset, int pageSize);
}
