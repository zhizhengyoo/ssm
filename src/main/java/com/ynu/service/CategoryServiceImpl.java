package com.ynu.service;

import com.ynu.dto.Category;
import com.ynu.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YANG on 2017/2/17.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> selectFirstLevel(){
        return categoryMapper.selectFirstLevel();
    }

    public List<Category> selectSecondLevel(Integer categoryId){
        return categoryMapper.selectSecondLevel(categoryId);
    }

    public Category selectBycId(Integer id){
        return categoryMapper.selectBycId(id);
    }
}
