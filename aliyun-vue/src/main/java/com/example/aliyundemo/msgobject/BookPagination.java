package com.example.aliyundemo.msgobject;

import com.example.aliyundemo.jpa.entity.Book;
import lombok.Data;

import java.util.List;

@Data
public class BookPagination {
    private boolean hasMore;
    private List<Book> books;
}
