package com.ynu.controllers;

import com.ynu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ynu.service.UserServiceImpl;

/**
 * Created by Qixiangyu on 2017/2/10.
 */
@Controller
public class ApiTest {


    @RequestMapping("/test")
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("test","qixiangyu");
        //mv.addObject("user",userService.selectByUserName("Bob"));
        return mv;
    }



}
