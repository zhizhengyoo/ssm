package com.ynu.service;

import com.ynu.dto.Logistics;
import com.ynu.mapper.LogisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YANG on 2017/3/31.
 */

@Service
public class LogisticsServiceImpl implements LogisticsService {

    @Autowired
    private LogisticsMapper logisticsMapper;

    public void insert(Logistics logistics){
        logisticsMapper.insert(logistics);
    }

    public Logistics selectByLogistics(Logistics logistics){
        return logisticsMapper.selectByLogistics(logistics);
    }
}
