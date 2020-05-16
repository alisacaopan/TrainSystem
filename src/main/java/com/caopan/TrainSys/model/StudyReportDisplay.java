package com.caopan.TrainSys.model;



public class StudyReportDisplay {

    private long reportId;
    private Integer isFinish;
    private float studyTime;  //分钟
    private long userId;
    private long vCourseId;
    private String vCourseName;

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
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

    public String getvCourseName() {
        return vCourseName;
    }

    public void setvCourseName(String vCourseName) {
        this.vCourseName = vCourseName;
    }
}
