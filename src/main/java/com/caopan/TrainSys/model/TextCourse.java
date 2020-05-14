package com.caopan.TrainSys.model;



public class TextCourse {
    private long tCourseId;
    private String name;
    private String address;
    private String introduce;
    private Integer classifyId;

    public TextCourse() {
        this.tCourseId = (long)0;
        this.name = "";
        this.address = "";
        this.introduce = "";
        this.classifyId = 0;
    }

    public long gettCourseId() {
        return tCourseId;
    }

    public void settCourseId(long tCourseId) {
        this.tCourseId = tCourseId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
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
