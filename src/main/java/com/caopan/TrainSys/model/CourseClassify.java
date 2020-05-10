package com.caopan.TrainSys.model;



public class CourseClassify {
    private Integer classifyId;
    private String name;
    private Integer level;
    private Integer parentId;

    public CourseClassify() {
        this.classifyId = 0;
        this.name = "";
        this.level = 0;
        this.parentId = 0;
    }

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
