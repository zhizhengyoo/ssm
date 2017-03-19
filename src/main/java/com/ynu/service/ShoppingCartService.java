package com.ynu.service;

import com.ynu.dto.ShoppingCart;

import java.util.List;

/**
 * Created by YANG on 2017/3/13.
 */
public interface ShoppingCartService {

    public void insert(Integer bookId,Integer userId,Integer counts);

    public List<ShoppingCart> selectByUserId(Integer userId);
}
