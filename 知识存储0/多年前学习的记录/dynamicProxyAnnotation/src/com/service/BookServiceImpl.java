package com.service;

public class BookServiceImpl implements BookService {
    @Override
    public void addBook() throws Exception {
        System.out.println("book add");
    }

    @Override
    public void updateBook() throws Exception {
        System.out.println("book update");
    }

    @Override
    public void deleteBook() throws Exception {
        System.out.println("book delete");
    }

    @Override
    public void searchBook() throws Exception {
        System.out.println("book search");
    }
}
