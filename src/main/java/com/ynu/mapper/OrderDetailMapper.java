package com.ynu.mapper;

import com.ynu.dto.OrderDetail;

import java.util.List;

/**
 * Created by YANG on 2017/3/21.
 */
public interface OrderDetailMapper {

    public void insert(OrderDetail orderDetail);

    public List<OrderDetail> selectByOrderId(Integer orderId);

    public void delete(OrderDetail orderDetail);
}
