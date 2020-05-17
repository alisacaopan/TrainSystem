package com.caopan.TrainSys.model;



public class Question {
    private long quesId;
    private String quesContent;
    private String quesType;
    private int classifyId;

    public Question() {
        this.quesId = (long)0;
        this.quesContent = "";
        this.quesType = "";
        this.classifyId = 0;
    }

    public long getQuesId() {
        return quesId;
    }

    public void setQuesId(long quesId) {
        this.quesId = quesId;
    }

    public String getQuesContent() {
        return quesContent;
    }

    public void setQuesContent(String quesContent) {
        this.quesContent = quesContent;
    }

    public String getQuesType() {
        return quesType;
    }

    public void setQuesType(String quesType) {
        this.quesType = quesType;
    }

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }
}
