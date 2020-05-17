package com.caopan.TrainSys.model;

public class UserSign {
    private long SignInId;
    private String SignInTime;
    private String SignStatus;
    private long userId;
    private String userName;
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public long getSignInId() {
        return SignInId;
    }

    public void setSignInId(long signInId) {
        SignInId = signInId;
    }

    public String getSignInTime() {
        return SignInTime;
    }

    public void setSignInTime(String signInTime) {
        SignInTime = signInTime;
    }

    public String getSignStatus() {
        return SignStatus;
    }

    public void setSignStatus(String signStatus) {
        SignStatus = signStatus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
