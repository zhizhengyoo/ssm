package com.ynu.service;

import com.ynu.dto.User;

/**
 * Created by YANG on 2017/2/14.
 */
public interface UserService {
    public void insertUser(User user);
    public User userLogin(String userName,String password);
}
