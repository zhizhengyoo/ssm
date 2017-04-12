package com.ynu.mapper;

import com.ynu.dto.Address;

import java.util.List;

/**
 * Created by YANG on 2017/4/7.
 */
public interface AddressMapper {
    public void insert(Address address);
    public List<Address> selectById(Address address);
}
