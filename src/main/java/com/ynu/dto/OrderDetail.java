package com.ynu.dto;

import java.sql.Timestamp;

/**
 * Created by YANG on 2017/3/21.
 */
public class OrderDetail extends Book {

    private Integer orderDetailId;
    private Integer orderId;
    private Integer bookId;
    private Integer counts;

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

}
