package com.ynu.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ynu.dto.Book;
import com.ynu.dto.BookDetailImg;
import com.ynu.dto.Category;
import com.ynu.service.BookDetailImgService;
import com.ynu.service.BookService;
import com.ynu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by YANG on 2017/3/14.
 */


@Service
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookDetailImgService bookDetailImgService;

    @RequestMapping("/bookDetail/{id}")
    public String bookDetail(@PathVariable Integer id ,
                             Model model,
                             HttpServletRequest request) {
        Book book = bookService.selectByBookId(id);
        Category category = new Category();
        if(book.getCategoryId()!=null && book.getCategoryId()>=0){
            category = categoryService.selectBycId(book.getCategoryId());
            if (category.getParentCId()!=null && category.getParentCId()>=0){
                category = categoryService.selectBycId(category.getParentCId());
            }
        }
        if (category!=null){
            book.setCategoryParentName(category.getCategoryName());
        }else {
            book.setCategoryParentName("");
        }
        List<BookDetailImg> bookDetailImgs = bookDetailImgService.selectByBookId(id);
        model.addAttribute("book",book);
        model.addAttribute("details",bookDetailImgs);
        if (request.getSession().getAttribute("addShoppingCartSuccess") != null){
            model.addAttribute("addShoppingCartSuccess","添加购物车成功");
            request.getSession().removeAttribute("addShoppingCartSuccess");
        }
        return "bookDetail";
    }

    @RequestMapping("/bookShow/{id}")
    public String BookShow(@PathVariable Integer id, Model model){
        Book book = new Book();
        book.setCategoryId(id);
        List<Book> books = bookService.selectBookList(book);
        model.addAttribute("books",books);
        return "book_show";
    }
}
