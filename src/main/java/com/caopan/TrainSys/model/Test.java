package com.caopan.TrainSys.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    private long testId ;
    private float grade;
    private String testTime;
    private String testRecord;
    private long userId ;
    private long vCourseId ;

    public Test() {
        this.testId = (long)0;
        this.grade = 0;
        this.testTime = "";
        this.testRecord = "";
        this.userId = (long)0;
        this.vCourseId = (long)0;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public String getTestRecord() {
        return testRecord;
    }

    public void setTestRecord(String testRecord) {
        this.testRecord = testRecord;
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
