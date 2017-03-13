package com.ynu.service;

import com.ynu.dto.Book;
import com.ynu.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YANG on 2017/2/17.
 */

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    public void insertBook(Book book){

        bookMapper.insertBook(book);
    }
    public Book selectLasted(){
        return bookMapper.selectLasted();
    }

    public List<Book> selectByuserId(Integer userId){
        return bookMapper.selectByuserId(userId);
    }
    public Book selectByBookId(Integer bookId){
        return bookMapper.selectByBookId(bookId);
    }
    public void updateBook(Book book){
        bookMapper.updateBook(book);
    }
    public void destroyBook(Integer bookId){
        bookMapper.destroyBook(bookId);
    }
    public List<Book> selectAll(){
        return bookMapper.selectAll();
    }
}
