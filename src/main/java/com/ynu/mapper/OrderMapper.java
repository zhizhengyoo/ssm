package com.ynu.mapper;

import com.ynu.dto.Order;
import com.ynu.dto.ShoppingCart;

import java.util.List;

/**
 * Created by YANG on 2017/3/21.
 */
public interface OrderMapper {
    public void insert(Order order);

    public Order selectLasted();

    public List<Order> selectStatusByUserId(Order order);

    public void updateStatus(Order order);
}
