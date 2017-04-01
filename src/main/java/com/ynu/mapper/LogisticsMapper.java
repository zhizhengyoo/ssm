package com.ynu.mapper;

import com.ynu.dto.Logistics;

/**
 * Created by YANG on 2017/3/31.
 */
public interface LogisticsMapper {

    public void insert(Logistics logistics);

    public Logistics selectByLogistics(Logistics logistics);
}
