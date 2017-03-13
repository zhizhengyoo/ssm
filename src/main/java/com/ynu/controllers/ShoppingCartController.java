package com.ynu.controllers;

import com.ynu.dto.Book;
import com.ynu.dto.ShoppingCart;
import com.ynu.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by YANG on 2017/3/13.
 */

@Service
@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/shoppingCart/{id}")
    public ModelAndView shoppingCart(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("home");
        /*mv.addObject("home","qixiangyu");*/
        return mv;
    }
}
