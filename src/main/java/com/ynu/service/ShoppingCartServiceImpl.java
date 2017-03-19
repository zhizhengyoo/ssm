package com.ynu.service;

import com.ynu.dto.ShoppingCart;
import com.ynu.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YANG on 2017/3/13.
 */

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    public void insert(Integer bookId,Integer userId,Integer counts){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCounts(counts);
        shoppingCart.setUserId(userId);
        shoppingCart.setBookId(bookId);
        shoppingCartMapper.insert(shoppingCart);
    }
}
