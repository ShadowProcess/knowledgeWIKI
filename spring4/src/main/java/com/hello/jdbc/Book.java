package com.hello.jdbc;

import java.io.Serializable;
import java.math.BigDecimal;


public class Book implements Serializable {

    private Long bookId;
    private String bookCover;
    private String bookName;
    private String bookInfo;
    private BigDecimal bookPrice;

    public Book() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookCover='" + bookCover + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookInfo='" + bookInfo + '\'' +
                ", bookPrice=" + bookPrice +
                '}';
    }
}
