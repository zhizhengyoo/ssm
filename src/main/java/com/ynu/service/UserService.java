package com.ynu.service;

import com.ynu.dto.User;

import java.util.List;

/**
 * Created by YANG on 2017/2/14.
 */
public interface UserService {
    public void insertUser(User user);
    public User userLogin(String userName,String password);
    public User selectByUserId(Integer userId);
    public List<User> userValidator(User user);
    public User adminLogin(User user);
    public void activateUser(User user);
    public void updateUser(User user);
    public void updateAccount(User user);
}
