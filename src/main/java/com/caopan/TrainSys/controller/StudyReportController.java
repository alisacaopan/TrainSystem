package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.dao.UserDao;
import com.caopan.TrainSys.biz.service.StudyReportService;
import com.caopan.TrainSys.biz.service.VideoCourseService;
import com.caopan.TrainSys.constant.StudyReportStatus;
import com.caopan.TrainSys.model.StudyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudyReportController {
    @Autowired
    private StudyReportService studyService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private VideoCourseService videoCourseService;

    @PostMapping(value = "/insertStudyReport")
    public Integer insert(@RequestBody StudyReport report) {
        int index = 0;
        try {
            if (studyService.insert(report) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @PostMapping(value = "/deleteStudyReport")
    public Integer deleteTest(@RequestParam(value = "reportId") Long reportId) {
        int index = 0;
        try {
            if (studyService.delete(reportId) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @PostMapping("/getStudyReport")
    public List<StudyReport> getAnwser(@RequestParam("userId") Long userId) {
        int index = 0;
        try {
            if (studyService.getStudyReport(userId).size() > 0) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return studyService.getStudyReport(userId);
            } else {
                return null;
            }

        }
    }


    @PostMapping("/recordStudy")
    public int recordStudy(@RequestParam("openId") String openId,
                           @RequestParam("vCourseId") Long vCourseId,
                           @RequestParam("duration") Long duration) {
        long usrId=userDao.getUserByOpenId(openId).getId();
        try {
            StudyReport studyReport=studyService.getReport(usrId,vCourseId);
            if (studyReport==null){
                StudyReport studyReportn=new StudyReport();
                studyReportn.setIsFinish(StudyReportStatus.STUDYING);
                studyReportn.setUserId(usrId);
                studyReportn.setvCourseId(vCourseId);
                studyReportn.setStudyTime(duration);
                return insert(studyReportn);
            }
            else if (studyReport.getIsFinish()==StudyReportStatus.FINISHED){
                studyReport.setStudyTime(studyReport.getStudyTime()+duration);
                return insert(studyReport);
            }else {
                studyReport.setStudyTime(studyReport.getStudyTime()+duration);
                float totalTime=videoCourseService.getOneCourse(studyReport.getvCourseId()).getTotalTime();
                if (studyReport.getStudyTime()>=totalTime)
                    studyReport.setIsFinish(StudyReportStatus.FINISHED);
                return insert(studyReport);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
