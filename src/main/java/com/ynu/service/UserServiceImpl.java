package com.ynu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ynu.dto.User;
import com.ynu.mapper.UserMapper;

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
    public User userLogin(User user){
        return mapper.userLogin(user);
    }

}
