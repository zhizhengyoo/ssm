package com.ynu.controllers;

import com.sun.javafx.sg.prism.NGShape;
import com.ynu.dto.*;
import com.ynu.mapper.BookDetailImgMapper;
import com.ynu.service.*;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by YANG on 2017/2/16.
 */

@Service
@Controller
public class CommonController {

    @Autowired
    private UserService userService;

    @Autowired
    private UseHoursService useHoursService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDetailImgMapper bookDetailImgMapper;

    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        List<Book> booksNew = bookService.selectNew();
        List<Book> booksHot = bookService.selectHot();
        List<Book> books = bookService.selectAll();
        mv.addObject("books",books);
        mv.addObject("booksNew",booksNew);
        mv.addObject("booksHot",booksHot);
        return mv;
    }
    @RequestMapping("/nav")
    @ResponseBody
    public Map<String ,List<Category>> nav(Model model){
        List<Category> category = categoryService.selectFirstLevel();
        model.addAttribute("firstLevel",category);
        List<Category> categories = categoryService.second();
        model.addAttribute("secondLevel",categories);
        Map<String,List<Category>> category2 = new HashMap<String, List<Category>>();
        category2.put("firstLevel",category);
        category2.put("secondLevel",categories);
        return category2;
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        return "login";
    }

    @RequestMapping(value = "/account_seller")
    public String AccountSeller(Model model, HttpSession session){
        return  "account_seller";
    }
    @RequestMapping(value = "/account_seller/publish_book_info")
    public String AccountSellerPublish(Model model, HttpSession session){
        List<UseHours> useHourses = useHoursService.selectAll();
        List<Category> categoriesFirst = categoryService.selectFirstLevel();
        model.addAttribute("useHoursList",useHourses);
        model.addAttribute("firstCategoryList",categoriesFirst);
        return  "publish_book";
    }

    @ResponseBody
    @RequestMapping(value = "/account_seller/secondcategory")
    public  List<Category> SecondCategory(@RequestParam("parentcid")String parentCId, Model model, HttpSession session){
        Integer id = Integer.parseInt(parentCId);
        List<Category> categoriesSecond = categoryService.selectSecondLevel(id);
        model.addAttribute("secondCategoryList",categoriesSecond);
        return  categoriesSecond;
    }




    @RequestMapping(value = "/account_seller/addBook")
    public String addBook(@RequestParam("coverImg")MultipartFile file,
                          @RequestParam("file") MultipartFile[] detailImg,
                          @RequestParam("bookName")String bookName,
                          @RequestParam("publishingCompany")String publishingCompany,
                          @RequestParam("author") String author,
                          @RequestParam("useHoursId") String useHoursId,
                          @RequestParam("price") BigDecimal price,
                          @RequestParam("counts") Integer counts,
                          @RequestParam("categoryId") String categoryId,
                          @RequestParam("parentCId") String parentCId,
                          @RequestParam("bookInfo") String bookInfo,
                          @RequestParam("freight") BigDecimal freight,
                          HttpServletRequest request,
                          HttpServletResponse response){
        String coverName = file.getOriginalFilename();
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        Object user  = request.getSession().getAttribute("login_success");
        String userName = "";
        int userId = ((User) user).getUserId();
        Book book = new Book();
        book.setUserId(userId);
        book.setAuthor(author);
        if (bookInfo!=null){
            book.setBookInfo(bookInfo);
        }
        book.setBookName(bookName);
        book.setUseHoursId(Integer.parseInt(useHoursId));
        book.setPrice(price);
        if (publishingCompany!=null){
            book.setPublishingCompany(publishingCompany);
        }
        if (categoryId!=null){
            if (Integer.parseInt(categoryId)>=0){
                book.setCategoryId(Integer.parseInt(categoryId));
            }else if ((Integer.parseInt(parentCId)>=0)){
                book.setCategoryId(Integer.parseInt(parentCId));
            }
    }
        book.setTotalNum(counts);
        book.setRemainNum(counts);
        if (freight!=null){
            book.setFreight(freight);
        }else{
            book.setFreight(BigDecimal.ZERO);
        }
        try{
            userName = ((User) user).getUserName();
        }catch (Exception e){
            return e.getMessage();
        }

        String path = rootPath + "static\\images\\"+userName+"\\";
        File userPath = new File(path);
        try {
            if (!userPath.exists()) {
                userPath.mkdir();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        try {
            String coverPath = path+"cover"+"\\";
            try {
                if (!(new File(coverPath).isDirectory())) {
                    new File(coverPath).mkdir();
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            coverPath = coverPath+coverName;
            file.transferTo(new File(coverPath));

            String sqlPath = coverPath.substring(rootPath.length());
            book.setCover(sqlPath);
            System.out.println("文件成功上传到指定目录下");
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        bookService.insertBook(book);
        Book bookLasted = bookService.selectLasted();
        int bookId = bookLasted.getBookId();
        BookDetailImg bookDetailImg = new BookDetailImg();
        String detailPath = path+"detail\\";
        try {
            if (!(new File(detailPath).isDirectory())) {
                new File(detailPath).mkdir();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
       if (detailImg!=null){
           for(MultipartFile detail:detailImg){
               String name = detail.getOriginalFilename();
               detailPath = detailPath+name;
               bookDetailImg.setBookId(bookId);
               bookDetailImg.setBookDetailImg(detailPath.substring(rootPath.length()));
               try{
                   detail.transferTo(new File(detailPath));
                   bookDetailImgMapper.insertBookDetailImg(bookDetailImg);
               }catch (Exception e){
                   e.printStackTrace();
               }

           }
       }
        return "redirect:/account_seller/onsellingBook";
    }

    @RequestMapping(value = "/account_seller/onsellingBook")
    public String onsellingBook(){
        return "onselling_book";
    }


    @RequestMapping(value = "/account_seller/onsellingBook/query")
    @ResponseBody
    public List<Book> queryOnsellingBook(HttpServletRequest request,
                                         HttpServletResponse response){
        User user  = (User) request.getSession().getAttribute("login_success");
        int userId =  user.getUserId();
        List<Book> books = bookService.selectByuserId(userId);
        List<Book> books1 = new ArrayList<Book>();
        for (Book book :books){
            if(book.getRemainNum()>0){
                if (book.getCategoryId()!=null && book.getCategoryId()>=0){
                    Category category = categoryService.selectBycId(book.getCategoryId());
                    Category category1 = categoryService.selectBycId(category.getParentCId());
                    if (category1!=null){
                        book.setCategoryParentName(category1.getCategoryName());
                    }
                }
                books1.add(book);
            }
        }
        return books1;

    }


    @RequestMapping(value = "/account_seller/onsellingBook/update",method = RequestMethod.POST)
    @ResponseBody
    public Book updateBook(@RequestBody Book book,HttpServletRequest request){

        Book book1 = bookService.selectByBookId(book.getBookId());
        if (book1.getRemainNum()!=book.getRemainNum()){
            book.setTotalNum(book.getRemainNum()+book.getSoldNum());
        }

        try {
            bookService.updateBook(book);
            return book;
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.getMessage();
        }
        return null;
    }
    @RequestMapping(value = "/account_seller/onsellingBook/destroy",method = RequestMethod.POST)
    @ResponseBody
    public Book destroyBook(@RequestBody Book book,HttpServletRequest request){
        Book book1 = book;
        bookService.destroyBook(book.getBookId());
        return book;
    }

    @RequestMapping("account")
    public String accountPage(){
        return "account";
    }

    @RequestMapping("accountDetail")
    public String accountDetailPage(){
        return "accountDetail";
    }
}
