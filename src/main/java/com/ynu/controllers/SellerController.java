package com.ynu.controllers;

import com.ynu.dto.Order;
import com.ynu.dto.User;
import com.ynu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by YANG on 2017/3/23.
 */
@Controller
public class SellerController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/account_seller/hasSold")
    public String hasSold(){
        return "hasSold_book";
    }

    @RequestMapping("/account_seller/hasSold/query")
    @ResponseBody
    public List<Order> hasSoldQuery(HttpServletRequest request){
        User user  = (User)request.getSession().getAttribute("login_success");
        Order order = new Order();
        order.setSellerId(user.getUserId());
        return  orderService.selectStatusByUserId(order);
    }

    @RequestMapping("/account_seller/fillBook")
    public String fillBook(){
        return "fill_book";
    }

    @RequestMapping("/account_seller/fillBook/query")
    @ResponseBody
    public List<Order> fillBookQuery(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("login_success");
        Order order = new Order();
        order.setSellerId(user.getUserId());
        order.setStatus(1);
        return orderService.selectStatusByUserId(order);
    }

}



