package com.ynu.service;

import com.ynu.dto.Book;

import java.util.List;

/**
 * Created by YANG on 2017/2/17.
 */
public interface BookService {

    public void insertBook(Book book);
    public Book selectLasted();
    public List<Book> selectByuserId(Integer userId);
    public Book selectByBookId(Integer bookId);
    public void updateBook(Book book);
    public void destroyBook(Integer bookId);
}
