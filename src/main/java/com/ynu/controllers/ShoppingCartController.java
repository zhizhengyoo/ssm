package com.ynu.controllers;

import com.ynu.dto.Address;
import com.ynu.dto.Book;
import com.ynu.dto.ShoppingCart;
import com.ynu.dto.User;
import com.ynu.service.AddressService;
import com.ynu.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.Filter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by YANG on 2017/3/13.
 */

@Controller
@Service
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AddressService addressService;

    @RequestMapping("/shoppingCart/{id}")
    public String insertShoppingCart(@PathVariable Integer id,
            @RequestParam(value = "bookId",required = false) Integer bookId,
                                     Model model,
                                     HttpSession session,
                                     @RequestParam(value = "counts",required = false) Integer counts,
            HttpServletRequest request, HttpServletResponse response) {
        String path = (String) request.getSession().getAttribute("returnUrl");
        if (bookId != null){
            User user = (User) request.getSession().getAttribute("login_success");
            Integer userId = user.getUserId();
            shoppingCartService.insert(bookId,userId,counts);
            session.setAttribute("addShoppingCartSuccess",true);
            System.out.println("success");
        }
        return "redirect:/bookDetail/"+id;
    }

    @RequestMapping("shoppingCart")
    public String shoppingCartPage(HttpServletRequest request,Model model){
        User user = (User)request.getSession().getAttribute("login_success");
        Address address = new Address();
        address.setUserId(user.getUserId());
        List<Address> addresses =addressService.selectById(address);
        model.addAttribute("addresses",addresses);
        return "shopping_cart";
    }

    @RequestMapping("shoppingCarts/query")
    @ResponseBody
    public List<ShoppingCart> shoppingCartQuery(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("login_success");
        List<ShoppingCart> shoppingCarts = shoppingCartService.selectByUserId(user.getUserId());
        return shoppingCarts;
    }

    @RequestMapping("shoppingCarts/update")
    @ResponseBody
    public ShoppingCart shoppingCartUpdate(HttpServletRequest request, @RequestBody ShoppingCart shoppingCart){
        shoppingCartService.update(shoppingCart);
        return shoppingCart;
    }

    @RequestMapping("shoppingCarts/delete")
    @ResponseBody
    public ShoppingCart shoppingCartDelete(HttpServletRequest request, @RequestBody ShoppingCart shoppingCart){
        shoppingCartService.delete(shoppingCart);
        return shoppingCart;
    }



}
