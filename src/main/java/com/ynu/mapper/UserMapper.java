/*
 * #{copyright}#
 */

package com.ynu.mapper;


import com.ynu.dto.User;

import java.util.List;


public interface UserMapper {
   public void insertUser(User user);
   public User userLogin(User user);
   public User selectByUserId(Integer userId);
   public List<User> userValidator(User user);
   public User adminLogin(User user);
   public void activateUser(User user);
   public void updateUser(User user);
   public void updateAccount(User user);
}