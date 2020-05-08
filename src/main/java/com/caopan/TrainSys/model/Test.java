package com.caopan.TrainSys.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    private long testId ;
    private Integer grade;
    private String testTime;
    private long userId ;
    private long vCourseId ;

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getvCourseId() {
        return vCourseId;
    }

    public void setvCourseId(long vCourseId) {
        this.vCourseId = vCourseId;
    }
}
