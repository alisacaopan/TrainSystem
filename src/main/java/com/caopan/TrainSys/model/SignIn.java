package com.caopan.TrainSys.model;

public class SignIn {
    private long signInId;
    private String signInTime;
    private String signStatus;
    private long userId;

    public long getSignInId() {
        return signInId;
    }

    public void setSignInId(long signInId) {
        this.signInId = signInId;
    }

    public String getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(String signInTime) {
        this.signInTime = signInTime;
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
