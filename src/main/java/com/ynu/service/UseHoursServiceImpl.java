package com.ynu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynu.dto.UseHours;
import com.ynu.mapper.UseHoursMapper;

import java.util.List;

/**
 * Created by YANG on 2017/2/16.
 */

@Service
@Transactional
public class UseHoursServiceImpl implements UseHoursService {

    @Autowired
    private UseHoursMapper useHoursMapper;

    public List<UseHours> selectAll(){
        return useHoursMapper.selectAll();
    }
}
