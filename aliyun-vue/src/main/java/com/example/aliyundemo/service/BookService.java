package com.example.aliyundemo.service;

import com.example.aliyundemo.jpa.entity.Book;
import com.example.aliyundemo.jpa.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public boolean addBook(Book book){
        bookRepository.save(book);
        return true;
    }


    public boolean deleteBook(Long bookId){
        bookRepository.deleteById(bookId);
        return true;
    }

    public Book queryBook(Long bookId){
        Optional<Book> byId = bookRepository.findById(bookId);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }


    public boolean editBook(Long booId, Book bookNew){
        Book book = bookRepository.findById(booId).get();
        if (book == null) {
            return false;
        }
        book.setBookCover(bookNew.getBookCover());
        book.setBookName(bookNew.getBookName());
        book.setBookInfo(bookNew.getBookInfo());
        book.setBookPrice(bookNew.getBookPrice());
        bookRepository.save(book);
        return true;
    }


    /**
     * 分页查询方法1
     * @param pageNum
     * @return
     */
    public Page<Book> findPage(int pageNum){
        Pageable pageable = PageRequest.of(pageNum,5);
        Page<Book> all = bookRepository.findAll(pageable);
        return all;
    }

    /**
     * 分页查询方法2
     * @return
     */
    public List<Book> findPage2(int offset, int pageSize){
        List<Book> books = bookRepository.queryPage(offset, pageSize);
        return books;
    }


    public List<Book> findAll(){
        List<Book> all = bookRepository.findAll();
        return all;
    }


}
