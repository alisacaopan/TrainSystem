package com.caopan.TrainSys.model;



public class StudyReport {
    private long reportId;
    private Integer isFinish;
    private float studyTime;
    private long userId;
    private long vCourseId;

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public float getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(float studyTime) {
        this.studyTime = studyTime;
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
