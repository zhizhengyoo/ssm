package com.ynu.service;

import com.ynu.dto.Book;
import com.ynu.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YANG on 2017/2/17.
 */

@Service
@Transactional
public class BookServiceImpl {

    @Autowired
    private BookMapper bookMapper;

    public void insertBook(Book book){
        bookMapper.insertBook(book);
    }
}
