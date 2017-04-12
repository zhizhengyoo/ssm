package com.ynu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ynu.dto.User;
import com.ynu.mapper.UserMapper;
import com.ynu.MD5Util;

import java.util.List;

/**
 * Created by Qixiangyu on 2017/2/13.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper mapper;

    public void insertUser(User user){
         mapper.insertUser(user);
    }
    public User userLogin(String userName,String password){
        User user = new User();
        user.setPassword(MD5Util.getPwd(password));
        if (userName.contains("@")){
            user.setEmail(userName);
        }else {
            user.setPhone(userName);
        }
        return mapper.userLogin(user);
    }

    public User selectByUserId(Integer userId){
        return mapper.selectByUserId(userId);
    }

    public List<User> userValidator(User user){
        return mapper.userValidator(user);
    }
    public User adminLogin(User user){
        return mapper.adminLogin(user);
    }

    public void activateUser(User user){
        mapper.activateUser(user);
    }
    public void updateUser(User user){
        mapper.updateUser(user);
    }
    public void updateAccount(User user){
        mapper.updateAccount(user);
    }
}
