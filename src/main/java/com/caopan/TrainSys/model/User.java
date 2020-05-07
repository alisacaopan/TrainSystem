package com.caopan.TrainSys.model;


public class User {
    private long id;
    private String name;
    private String password;
    private String mobile;
    private String idCard;
    private String openId;
    private String role;
    private int classId;

    public User() {

    }

    public User(String name, String password, String mobile, String idCard, String openId, String role, int classId) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.idCard = idCard;
        this.openId = openId;
        this.role = role;
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
