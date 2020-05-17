package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.dao.UserDao;
import com.caopan.TrainSys.biz.service.StudyReportService;
import com.caopan.TrainSys.biz.service.VideoCourseService;
import com.caopan.TrainSys.constant.StudyReportStatus;
import com.caopan.TrainSys.model.StudyReport;
import com.caopan.TrainSys.model.StudyReportDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping(value = "/updateStudyReport")
    public Integer update(@RequestBody StudyReport report) {
        int index = 0;
        try {
            if (studyService.update(report) == 1) {
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

    @GetMapping("/getStudyReport")
    public List<StudyReport> getStudyReport(@RequestParam("openId") String openId) {
        long userId = userDao.getUserByOpenId(openId).getId();
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

    @GetMapping("/getStudyReportDisplay")
    public List<StudyReportDisplay> getStudyReportDisplay(@RequestParam("openId") String openId) {
        List<StudyReportDisplay> studyReportDisplayList = new ArrayList<>();
        long userId = userDao.getUserByOpenId(openId).getId();
        try {
            List<StudyReport> studyReport = studyService.getStudyReport(userId);
            if(studyReport!=null){
                for (int i = 0; i< studyReport.size(); i++){
                    StudyReportDisplay studyReportDisplay=new StudyReportDisplay();
                    studyReportDisplay.setReportId(studyReport.get(i).getReportId());
                    studyReportDisplay.setIsFinish(studyReport.get(i).getIsFinish());
                    studyReportDisplay.setStudyTime((int)(studyReport.get(i).getStudyTime()/60)+1);
                    studyReportDisplay.setUserId(studyReport.get(i).getUserId());
                    studyReportDisplay.setvCourseName(videoCourseService.getOneCourse(studyReport.get(i).getvCourseId()).getName());
                    studyReportDisplay.setvCourseId(studyReport.get(i).getvCourseId());
                    studyReportDisplayList.add(studyReportDisplay);
                    System.out.printf("+_____"+studyReportDisplayList.size());
                }
            } else {
                return null;
            }

        } catch (Exception e) {
        }finally {
            if (studyReportDisplayList != null){
                return studyReportDisplayList;
            } else {
                return null;
            }
        }
    }


    @PostMapping("/recordStudy")
    public int recordStudy(@RequestParam("openId") String openId,
                           @RequestParam("vCourseId") Long vCourseId,
                           @RequestParam("duration") Float duration) {
        long usrId=userDao.getUserByOpenId(openId).getId();
        try {
            StudyReport studyReport=studyService.getReport(usrId,vCourseId);
            if (studyReport==null){
                StudyReport studyReportn=new StudyReport();
                studyReportn.setIsFinish(StudyReportStatus.STUDYING);
                studyReportn.setUserId(usrId);
                studyReportn.setvCourseId(vCourseId);
                studyReportn.setStudyTime(duration);
                float totalTime=videoCourseService.getOneCourse(vCourseId).getTotalTime();
                if (studyReportn.getStudyTime()>=totalTime)
                    studyReportn.setIsFinish(StudyReportStatus.FINISHED);
                return insert(studyReportn);
            }
            else if (studyReport.getIsFinish()==StudyReportStatus.FINISHED){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
                studyReport.setStudyTime(studyReport.getStudyTime()+duration);
                return update(studyReport);
            }else {
                System.out.println("..............................");
                studyReport.setStudyTime(studyReport.getStudyTime()+duration);
                float totalTime=videoCourseService.getOneCourse(studyReport.getvCourseId()).getTotalTime();
                if (studyReport.getStudyTime()>=totalTime)
                    studyReport.setIsFinish(StudyReportStatus.FINISHED);
                return update(studyReport);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
