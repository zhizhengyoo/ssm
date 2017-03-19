package com.ynu.mapper;

import com.ynu.dto.BookDetailImg;

import java.util.List;

/**
 * Created by YANG on 2017/2/21.
 */
public interface BookDetailImgMapper {

    public void insertBookDetailImg(BookDetailImg bookDetailImg);

    public List<BookDetailImg> selectByBookId(Integer bookId);
}
