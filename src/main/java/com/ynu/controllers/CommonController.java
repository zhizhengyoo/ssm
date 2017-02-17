package com.ynu.controllers;

import com.ynu.dto.Category;
import com.ynu.dto.UseHours;
import com.ynu.dto.User;
import com.ynu.service.CategoryService;
import com.ynu.service.UseHoursService;
import com.ynu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping(value = "/account_seller")
    public String AccountSeller(Model model, HttpSession session){
        List<UseHours> useHourses = useHoursService.selectAll();
        model.addAttribute("useHours",useHourses);
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

    @RequestMapping(value = "/test1")
    public String test2(){
        return "test1";
    }




}
