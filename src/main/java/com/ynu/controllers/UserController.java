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
        if (user != null){
            if (user.getStatus() == 0){
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
                              @RequestParam("phone")String phone,
                              @RequestParam("password")String password){
        String path = (String)request.getSession().getAttribute("returnUrl");
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
}
