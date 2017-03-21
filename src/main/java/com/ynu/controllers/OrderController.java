package com.ynu.controllers;

import com.ynu.dto.ResponseData;
import com.ynu.dto.ShoppingCart;
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


}
