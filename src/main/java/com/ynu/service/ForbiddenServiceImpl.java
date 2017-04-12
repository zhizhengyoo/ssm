package com.ynu.service;

import com.ynu.dto.Forbidden;
import com.ynu.mapper.ForbiddenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YANG on 2017/4/6.
 */

@Service
public class ForbiddenServiceImpl implements ForbiddenService{

    @Autowired
    private ForbiddenMapper forbiddenMapper;

    public void insert(Forbidden forbidden){
        forbiddenMapper.insert(forbidden);
    }
}
