package com.ynu.mapper;

import com.ynu.dto.ShoppingCart;

import java.util.List;

/**
 * Created by YANG on 2017/3/13.
 */
public interface ShoppingCartMapper {

    public void insert(ShoppingCart shoppingCart);

    public List<ShoppingCart> selectByUserId(Integer userId);
}
