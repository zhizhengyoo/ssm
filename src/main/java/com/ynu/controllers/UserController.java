package com.ynu.controllers;

import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ynu.MD5Util;
import com.ynu.dto.Address;
import com.ynu.dto.Forbidden;
import com.ynu.dto.User;
import com.ynu.service.AddressService;
import com.ynu.service.ForbiddenService;
import com.ynu.service.UserService;
import com.ynu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * Created by YANG on 2017/2/14.
 */

@Service
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ForbiddenService forbiddenService;

    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerUser(@RequestParam("password") String[] password,
                               @RequestParam("userName") String userName,
                               @RequestParam("phone") String phone,
                               @RequestParam("validatorImg")MultipartFile file,
                               @RequestParam("school") String school,
                               @RequestParam("schoolNumber") String schoolNumber,
                               Model model,
                               HttpServletRequest request) {
        if (!password[0].equals(password[1])){
            model.addAttribute("error","两次密码不一致");
            return "register";
        }else {
            User user = new User();
            String coverName = file.getOriginalFilename();
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            String path = rootPath + "static\\images\\validator\\";
            File validatorPath = new File(path);
            try {
                if (!validatorPath.exists()) {
                    validatorPath.mkdir();
                }
                String imgPath = path + coverName;
                try{
                    file.transferTo(new File(imgPath));
                    String sqlPath = imgPath.substring(rootPath.length());
                    user.setValidatorImg(sqlPath);
                }catch (Exception e){
                    e.printStackTrace();
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            user.setPhone(phone);
            String password2 = MD5Util.getPwd(password[0]);
            user.setPassword(password2);
            user.setUserName(userName);
            user.setSchool(school);
            user.setSchoolNumber(schoolNumber);
            userService.insertUser(user);
            model.addAttribute("message", "注册成功");
            return "redirect:home";
        }
    }

    @RequestMapping("/userReg/validator")
    @ResponseBody
    public String Validator(@RequestBody User user){
        List<User> user1 = userService.userValidator(user);
        if (user1.size()>0){
            return "error";
        }else {

            return "success";
        }
    }

    @RequestMapping(value = "/logout",method =RequestMethod.GET)
    public String Logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:home";
    }

    @RequestMapping("/account/personalCenter")
    public String personal(HttpServletRequest request,Model model){
        User user = (User)request.getSession().getAttribute("login_success");
        Address address = new Address();
        address.setUserId(user.getUserId());
        List<Address> addresses = addressService.selectById(address);
        model.addAttribute("user",user);
        model.addAttribute("addresses",addresses);
        return "personCenter";
    }

    @RequestMapping("/account/personalCenter/edit")
    @ResponseBody
    public User personalEdit(HttpServletRequest request,Model model,@RequestBody User user){
        User user1 = (User)request.getSession().getAttribute("login_success");
       user.setUserId(user1.getUserId());
        userService.updateUser(user);
        user1 = userService.selectByUserId(user1.getUserId());
        request.getSession().setAttribute("login_success",user1);
        return user1;
    }

    @RequestMapping("/account/prepaid")
    public String prapaid(HttpServletRequest request,
                          @RequestParam("number")float number){
        User user = (User)request.getSession().getAttribute("login_success");
        user.setAccount(number+user.getAccount());
        userService.updateAccount(user);
        request.getSession().setAttribute("login_success",user);
        return "redirect:/account/personalCenter";
    }

    @RequestMapping("/reg")
    public String RegisterPage(){
        return "register";
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
        request.getSession().removeAttribute("returnUrl");
        if (user != null){
            if (user.getStatus() <= 0){
                model.addAttribute("login_error","账户未激活");
                return "login";
            }else {
                session.setAttribute("login_success",user);
                if (path != null){

                    return "redirect:"+path;
                }else {
                    return "redirect:home";
                }
            }
        }else{
            model.addAttribute("login_error","账号信息错误，请重新登陆");
            return "login";
        }

    }

    @RequestMapping(value = "/admini/index")
    public String test2(){
        return "admin/index";
    }

    @RequestMapping("/admini/login")
    public String adminLogin(){
        return "admin/login";
    }

    @RequestMapping("/admini/logins")
    public String adminLogins(HttpServletRequest request,
                              Model model,
                              HttpSession session,
                              @RequestParam("phone")String phone,
                              @RequestParam("password")String password){
        String path = (String)request.getSession().getAttribute("returnUrl");
        request.getSession().removeAttribute("returnUrl");
        User user = new User();
        user.setPhone(phone);
        user.setPassword(MD5Util.getPwd(password));
        User user1 = userService.adminLogin(user);
        if (user1 != null){
            request.getSession().setAttribute("admin_login_success",user1);
            if (path != null){

                return "redirect:"+path;
            }else {
                return "redirect:/admini/index";
            }
        }else {
            model.addAttribute("login_error","账号信息错误，请重新登陆");
            return "admin/login";
        }
    }

    @RequestMapping("/admini/validatorQuery")
    @ResponseBody
    public List<User> queryValidator(HttpServletRequest request){
        User user = new User();
        user.setStatus(0);
        List<User> users = userService.userValidator(user);
        return users;
    }

    @RequestMapping("/admini/activateUser")
    @ResponseBody
    public User activateUser(@RequestBody User user){
        user.setStatus(1);
        userService.activateUser(user);
        return user;
    }

    @RequestMapping("/admini/queryUser")
    public String queryUsers(@RequestParam("userId")Integer userId,
                             Model model){
        User user = userService.selectByUserId(userId);
        model.addAttribute("user",user);
        return "admin/forbidden_modal";
    }

    @RequestMapping("/admini/forbiddenUser")
    public String forbiddenUser(/*@RequestBody Forbidden forbidden,*/
                              @RequestParam("userId") Integer userId,
                              @RequestParam("reason") String reason,
                              HttpServletRequest request){
        Forbidden forbidden =new Forbidden();
        forbidden.setReason(reason);
        forbidden.setUserId(userId);
        User user = (User)request.getSession().getAttribute("admin_login_success");
        forbidden.setAdminId(user.getUserId());
        forbiddenService.insert(forbidden);
        User user1 = new User();
        user1.setStatus(-1);
        user1.setUserId(userId);
        userService.activateUser(user1);
        return "admin/index";
    }
}
