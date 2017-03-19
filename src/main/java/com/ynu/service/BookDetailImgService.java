package com.ynu.service;

import com.ynu.dto.BookDetailImg;

import java.util.List;

/**
 * Created by YANG on 2017/2/21.
 */
public interface BookDetailImgService {

    public void insertBookDetailImg(BookDetailImg bookDetailImg);
    public List<BookDetailImg> selectByBookId(Integer bookId);
}
