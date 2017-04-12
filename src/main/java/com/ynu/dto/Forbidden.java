package com.ynu.dto;


/**
 * Created by YANG on 2017/4/6.
 */
public class Forbidden {

    private Integer forbiddenId;
    private Integer adminId;
    private Integer userId;
    private String reason;

    public Integer getForbiddenId() {
        return forbiddenId;
    }

    public void setForbiddenId(Integer forbiddenId) {
        this.forbiddenId = forbiddenId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
