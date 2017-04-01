/*
 * #{copyright}#
 */

package com.ynu.mapper;


import com.ynu.dto.User;


public interface UserMapper {
   public void insertUser(User user);
   public User userLogin(User user);
   public User selectByUserId(Integer userId);
   public User userValidator(User user);
}