package com.ynu.service;

import com.ynu.dto.Book;

/**
 * Created by YANG on 2017/2/17.
 */
public interface BookService {

    public void insertBook(Book book);
    public Book selectLasted();
}
