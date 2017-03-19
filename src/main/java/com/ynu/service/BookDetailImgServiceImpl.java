package com.ynu.service;

import com.ynu.dto.BookDetailImg;
import com.ynu.mapper.BookDetailImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YANG on 2017/2/21.
 */

@Service
@Transactional
public class BookDetailImgServiceImpl implements BookDetailImgService {

    @Autowired
    private BookDetailImgMapper bookDetailImgMapper;

    public void insertBookDetailImg(BookDetailImg bookDetailImg){
        bookDetailImgMapper.insertBookDetailImg(bookDetailImg);
    }

    public List<BookDetailImg> selectByBookId(Integer bookId){
        return bookDetailImgMapper.selectByBookId(bookId);
    }
}
