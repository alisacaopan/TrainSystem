package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.StudyReportService;
import com.caopan.TrainSys.biz.service.TestService;
import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.StudyReport;
import com.caopan.TrainSys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudyReportController {
    @Autowired
    private StudyReportService studyService;

    @PostMapping(value = "/insertStudyReport")
    public Integer insert(@RequestBody StudyReport report) {
        int index = 0;
        try {
            if(studyService.insert(report) == 1){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
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
            if (studyService.getStudyReport(userId).size()>0) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1){
                return studyService.getStudyReport(userId);
            } else {
                return null;
            }

        }
    }

}
