package com.ynu.dto;

/**
 * Created by YANG on 2017/2/17.
 */
public class Category {

    private Integer categoryId;
    private String categoryName;
    private Integer parentCId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getParentCId() {
        return parentCId;
    }

    public void setParentCId(Integer parentCId) {
        this.parentCId = parentCId;
    }
}
