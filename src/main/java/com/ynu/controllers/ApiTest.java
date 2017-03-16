package com.ynu.controllers;

import com.ynu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ynu.service.UserServiceImpl;

/**
 * Created by Qixiangyu on 2017/2/10.
 */
@Controller
public class ApiTest {


  /* @RequestMapping("bookDetail")
    public ModelAndView bookDetail() {
        ModelAndView mv = new ModelAndView("bookDetail");
        /*//*mv.addObject("home","qixiangyu");*//**//*
        return mv;
    }*/

    @RequestMapping("/bookDetail")
    public String getBookDetail(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("bookDetail");
        /*mv.addObject("home","qixiangyu");*/
        return "bookDetail";
    }





}
