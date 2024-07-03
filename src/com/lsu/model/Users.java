package com.lsu.model;

public class Users {
    private Integer UserId;
    private String UserName;
    private String Password;
    private Integer Role;
    private String Sex;
    private String Phone;

    // getter and setter
    public String getSex() {
        return Sex;
    }
    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getPhone() {
        return Phone;
    }
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Integer getUserId() {
        return UserId;
    }
    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }

    public String getUserName() {
        return UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Integer getRole() {
        return Role;
    }
    public void setRole(Integer Role) {
        this.Role = Role;
    }

}
