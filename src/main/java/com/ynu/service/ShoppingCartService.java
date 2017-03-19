package com.ynu.service;

import com.ynu.dto.ShoppingCart;

/**
 * Created by YANG on 2017/3/13.
 */
public interface ShoppingCartService {

    public void insert(Integer bookId,Integer userId,Integer counts);
}
