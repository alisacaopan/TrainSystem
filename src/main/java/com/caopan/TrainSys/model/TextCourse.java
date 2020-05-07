package com.caopan.TrainSys.model;



public class TextCourse {
    private long tCourseId;
    private String name;
    private String address;
    private String introduce;
    private Integer classifyId;

    public long getvCourseId() {
        return tCourseId;
    }

    public void setvCourseId(long vCourseId) {
        this.tCourseId = vCourseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }
}
