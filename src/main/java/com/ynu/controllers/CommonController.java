package com.ynu.controllers;

import com.ynu.dto.UseHours;
import com.ynu.dto.User;
import com.ynu.service.UseHoursService;
import com.ynu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by YANG on 2017/2/16.
 */

@Service
@Controller
public class CommonController {

    @Autowired
    private UserService userService;

    @Autowired
    private UseHoursService useHoursService;

    @RequestMapping(value = "/account_seller")
    public String AccountSeller(Model model, HttpSession session){
        List<UseHours> useHourses = useHoursService.selectAll();
        model.addAttribute("useHours",useHourses);
        return  "account_seller";
    }
}
