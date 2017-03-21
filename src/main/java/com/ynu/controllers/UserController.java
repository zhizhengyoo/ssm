package com.ynu.controllers;

import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ynu.MD5Util;
import com.ynu.dto.User;
import com.ynu.service.UserService;
import com.ynu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by YANG on 2017/2/14.
 */

@Service
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerUser(@RequestParam("password") String password,
                               @RequestParam("userName") String userName,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               Model model,
                               HttpServletRequest request) {
        User user = new User();
        user.setPhone(phone);
        user.setEmail(email);
        password = MD5Util.getPwd(password);
        user.setPassword(password);
        user.setUserName(userName);
        userService.insertUser(user);
        model.addAttribute("message", "注册成功");
        return "redirect:home";
    }

    @RequestMapping(value = "/logout",method =RequestMethod.GET)
    public String Logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:home";
    }

    @RequestMapping(value = "/logins",method = RequestMethod.POST)
    public String Login(@RequestParam("userName") String userName,
                        @RequestParam("password")String password,
                        HttpSession session,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model){
        User user = userService.userLogin(userName,password);
        String path = (String)request.getSession().getAttribute("returnUrl");
        if (user != null){
            session.setAttribute("login_success",user);
            if (path != null){
                return "redirect:"+path;
            }else {
                return "redirect:home";
            }
        }else{
            model.addAttribute("login_error","账号信息错误，请重新登陆");
            return "login";
        }

    }


}
