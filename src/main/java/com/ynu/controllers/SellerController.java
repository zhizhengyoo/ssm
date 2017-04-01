package com.ynu.controllers;

import com.ynu.dto.Logistics;
import com.ynu.dto.Order;
import com.ynu.dto.User;
import com.ynu.service.LogisticsService;
import com.ynu.service.OrderService;
import com.ynu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by YANG on 2017/3/23.
 */
@Controller
public class SellerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private LogisticsService logisticsService;

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

    @RequestMapping("/account_seller/fillBook/modal")
    public String fillBookMadal(@RequestParam("id") Integer id, Model model, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("login_success");
        Order order = new Order();
        order.setOrderId(id);
        List<Order> orders = orderService.selectStatusByUserId(order);
        model.addAttribute("orders",orders);
        Order order1 = orders.get(0);
        User user1 = userService.selectByUserId(orders.get(0).getUserId());
        model.addAttribute("buyer",user1);
        return "fill_book_modal";
    }

    @RequestMapping(value = "/account_seller/fillBook/insert",method = RequestMethod.POST)
    @ResponseBody
    public List<Order> fillBookInsert(HttpServletRequest request, @RequestBody Logistics logistics){
        User user = (User)request.getSession().getAttribute("login_success");
        Order order = orderService.selectByOrderId(logistics.getOrderId());
        orderService.updateStatus(order,2);
        logisticsService.insert(logistics);
        return orderService.selectStatusByUserId(order);
    }

}



