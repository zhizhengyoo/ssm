package com.ynu.service;

import com.ynu.dto.Book;
import com.ynu.dto.Order;
import com.ynu.dto.OrderDetail;
import com.ynu.dto.ShoppingCart;
import com.ynu.mapper.OrderDetailMapper;
import com.ynu.mapper.OrderMapper;
import com.ynu.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YANG on 2017/3/21.
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private  BookService bookService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    public Order selectLasted(){
        return orderMapper.selectLasted();
    }

    public void insert(List<ShoppingCart> shoppingCarts){
        Map<Integer,List<ShoppingCart>> userBook= new HashMap<Integer,List<ShoppingCart>>();
        List<Order> orders = new ArrayList<Order>();
        for(ShoppingCart shoppingCart:shoppingCarts){
            Order order = new Order();
            Integer sellerId  = bookService.selectByBookId(shoppingCart.getBookId()).getUserId();
            Integer userId = shoppingCart.getUserId();
            if (userBook.containsKey(sellerId)){
                for (Order order2: orders){
                    if(sellerId == order2.getSellerId()){
                        order2.setFreight(shoppingCart.getFreight().add(order2.getFreight()));
                        order2.setBookPrice(order2.getBookPrice().add(shoppingCart.getPrice().multiply(new BigDecimal(shoppingCart.getCounts()))));
                        order2.setTotalPrice(order2.getBookPrice().add(order2.getFreight()));
                    }
                }
               userBook.get(sellerId).add(shoppingCart);
            }else {
                List<ShoppingCart> shoppingCarts2 = new ArrayList<ShoppingCart>();
                order.setBookPrice(shoppingCart.getPrice().multiply(new BigDecimal(shoppingCart.getCounts())));
                order.setFreight(shoppingCart.getFreight());
                order.setTotalPrice(order.getBookPrice().add(order.getFreight()));
                order.setUserId(userId);
                order.setSellerId(sellerId);
                order.setCreateTime(new Timestamp(System.currentTimeMillis()));
                orders.add(order);
                shoppingCarts2.add(shoppingCart);
                userBook.put(sellerId,shoppingCarts2);
            }
        }
        for (Order order :orders){
            orderMapper.insert(order);
            Integer orderId = orderMapper.selectLasted().getOrderId();
            if(userBook.containsKey(order.getSellerId())){
                for(ShoppingCart shoppingCart:userBook.get(order.getSellerId())){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderId(orderId);
                    orderDetail.setBookId(shoppingCart.getBookId());
                    orderDetail.setCounts(shoppingCart.getCounts());
                    orderDetailMapper.insert(orderDetail);
                    shoppingCartMapper.delete(shoppingCart);

                }
            }
        }
    }
}
