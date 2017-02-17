package com.ynu.mapper;

import com.ynu.dto.Category;

import java.util.List;

/**
 * Created by YANG on 2017/2/17.
 */
public interface CategoryMapper {

    public List<Category> selectFirstLevel();
    public List<Category> selectSecondLevel(Integer categoryId);
}
