package com.ynu.service;

import com.ynu.dto.OrderDetail;
import com.ynu.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YANG on 2017/3/21.
 */

@Service
public class OrderDetailServiceImpl {

    @Autowired
    private OrderMapper orderMapper;

    public void insert(OrderDetail orderDetail){
        orderMapper.insert(orderDetail);
    }
}
