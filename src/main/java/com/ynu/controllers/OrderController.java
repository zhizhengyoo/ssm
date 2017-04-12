package com.ynu.controllers;

import com.ynu.dto.*;
import com.ynu.service.BookService;
import com.ynu.service.OrderService;
import com.ynu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.FloatLiteral;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

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
                                          Model model,
                                          @RequestBody List<Order> orders){
        User user = (User)request.getSession().getAttribute("login_success");
        float account = user.getAccount();
        BigDecimal counts = new BigDecimal(Float.toString(account));
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Order order:orders){
            totalPrice = totalPrice.add(order.getTotalPrice());
        }
       if (totalPrice.compareTo(counts) > 0){
            model.addAttribute("pay_error","付款失败，余额不足！");
           return null;
        }else {
            user.setAccount(counts.subtract(totalPrice).floatValue());
            userService.updateAccount(user);
           for(Order order:orders){
               orderService.updateStatus(order,1);
               List<OrderDetail> orderDetails = order.getOrderDetails();
               for (OrderDetail orderDetail:orderDetails){
                   Book book = bookService.selectByBookId(orderDetail.getBookId());
                   int remainNum = book.getRemainNum();
                   int soldNum = book.getSoldNum();
                   book.setRemainNum(remainNum-orderDetail.getCounts());
                   book.setSoldNum(soldNum+orderDetail.getCounts());
                   bookService.updateBook(book);
               }
           }
           return orders;
       }
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

    @RequestMapping("/order/unConfirm")
    public String unConfirm(){
        return "unConfirm_book";
    }

    @RequestMapping("/order/unConfirm/query")
    @ResponseBody
    public List<Order> unConfirmQuery(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("login_success");
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setStatus(2);
        return orderService.selectStatusByUserId(order);
    }

    @RequestMapping("/order/unConfirm/insert")
    @ResponseBody
    public Order orderunConfirmInsert(HttpServletRequest request,
                                          @RequestBody Order order){
            orderService.updateStatus(order,3);
            User user = userService.selectByUserId(order.getSellerId());
            BigDecimal counts = order.getTotalPrice().add(new BigDecimal(Float.toString(user.getAccount())));
            user.setAccount(counts.floatValue());
            userService.updateAccount(user);
        return order;
    }

    @RequestMapping("/order/unComment")
    public String orderUnComment(){
        return "unComment_book";
    }

    @RequestMapping("/order/unComment/query")
    @ResponseBody
    public List<Order> orderUnCommentQuery(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("login_success");
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setStatus(3);
        return orderService.selectStatusByUserId(order);
    }
}
