package com.ynu.controllers;

import com.ynu.dto.*;
import com.ynu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by YANG on 2017/3/21.
 */
@Service
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/insert")
    @ResponseBody
    public List<ShoppingCart> orderInsert(HttpServletRequest request,
                                    @RequestBody List<ShoppingCart> shoppingCarts){
        orderService.insert(shoppingCarts);
        System.out.println(shoppingCarts);
        Object object = new Object();
        return shoppingCarts;
    }

    @RequestMapping("account/order/unPayment")
    public String accountUnpayment(){
        return "unPayment_orders";
    }

    @RequestMapping("/account/order/unPayment/query")
    @ResponseBody
    public List<Order> orders(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("login_success");
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setStatus(0);
        return orderService.selectStatusByUserId(order);
    }

    @RequestMapping("/account/order/unPayment/destroy")
    @ResponseBody
    public Order ordersDestroy(HttpServletRequest request, @RequestBody Order order){
        orderService.updateStatus(order,5);
        return order;
    }

    @RequestMapping("/order/payment/insert")
    @ResponseBody
    public List<Order> orderPaymentInsert(HttpServletRequest request,
                                          @RequestBody List<Order> orders){
        for(Order order:orders){
            orderService.updateStatus(order,1);
        }
        return orders;
    }

    @RequestMapping("/order/unFilled")
    public String orderUnFilled(){
        return "unfilled_orders";
    }

    @RequestMapping("/order/unFilled/query")
    @ResponseBody
    public List<Order> unFilledQuery(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("login_success");
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setStatus(1);
        return orderService.selectStatusByUserId(order);
    }


}
