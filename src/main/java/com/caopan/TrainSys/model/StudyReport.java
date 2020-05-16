package com.caopan.TrainSys.model;



public class StudyReport {
    private long reportId;
    private Integer isFinish;
    private float studyTime;
    private long userId;
    private long vCourseId;

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public StudyReport() {
        this.reportId = (long)0;
        this.isFinish = 0;
        this.studyTime = (float)0.0;
        this.userId = (long)0;
        this.vCourseId = (long)0;
    }

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
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
