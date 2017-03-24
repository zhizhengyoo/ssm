package com.ynu.service;

import com.ynu.dto.OrderDetail;

import java.util.List;

/**
 * Created by YANG on 2017/3/21.
 */
public interface OrderDetailService {

    public void insert(OrderDetail orderDetail);

    public List<OrderDetail> selectByOrderId(Integer orderId);
}
