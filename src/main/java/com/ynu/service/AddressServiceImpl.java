package com.ynu.service;

import com.ynu.dto.Address;
import com.ynu.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YANG on 2017/4/7.
 */

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    public void insert(Address address){
        addressMapper.insert(address);
    }
    public List<Address> selectById(Address address){
        return addressMapper.selectById(address);
    }
}
