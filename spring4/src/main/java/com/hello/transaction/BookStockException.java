package com.hello.transaction;

public class BookStockException extends RuntimeException {

    private static final long serialVersionUID = -8592464644696848125L;

    public BookStockException(String message) {
        super(message);
    }
}
