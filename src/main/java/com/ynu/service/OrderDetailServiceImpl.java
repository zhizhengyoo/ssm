package com.ynu.service;

import com.ynu.dto.OrderDetail;
import com.ynu.mapper.OrderDetailMapper;
import com.ynu.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YANG on 2017/3/21.
 */

@Service
public class OrderDetailServiceImpl {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public void insert(OrderDetail orderDetail){
        orderDetailMapper.insert(orderDetail);
    }


    public List<OrderDetail> selectByOrderId(Integer orderId){
        return orderDetailMapper.selectByOrderId(orderId);
    }
}
