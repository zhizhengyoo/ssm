package com.ynu.service;

import com.ynu.dto.Book;
import com.ynu.dto.Order;
import com.ynu.dto.ShoppingCart;

import java.util.List;

/**
 * Created by YANG on 2017/3/21.
 */
public interface OrderService {

    public void insert(List<ShoppingCart> shoppingCarts);

    public Order selectLasted();
}
