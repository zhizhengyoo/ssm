package com.ynu.service;

import com.ynu.dto.Logistics;

/**
 * Created by YANG on 2017/3/31.
 */
public interface LogisticsService {

    public void insert(Logistics logistics);

    public Logistics selectByLogistics(Logistics logistics);
}
