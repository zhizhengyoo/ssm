package com.ynu.controllers;

import com.ynu.dto.Address;
import com.ynu.dto.User;
import com.ynu.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YANG on 2017/4/7.
 */
@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("/account/address/insert")
    public String insertAddress(HttpServletRequest request,
                                @RequestParam("name")String name,
                                @RequestParam("phone")String phone,
                                @RequestParam("postCode")String postCode,
                                @RequestParam("addressName")String addressName){
        Address address = new Address();
        User user = (User)request.getSession().getAttribute("login_success");
        address.setName(name);
        address.setAddressName(addressName);
        if (postCode!=null){
            address.setPostCode(postCode);
        }
        address.setPhone(phone);
        address.setUserId(user.getUserId());
        addressService.insert(address);
        return "redirect:/account/personalCenter";
    }

}
