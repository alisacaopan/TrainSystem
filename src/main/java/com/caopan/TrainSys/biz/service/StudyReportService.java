package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.StudyDao;
import com.caopan.TrainSys.biz.dao.TestDao;
import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.Selection;
import com.caopan.TrainSys.model.StudyReport;
import com.caopan.TrainSys.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class StudyReportService {
    @Autowired
    private StudyDao studydao;

    public Integer insert(StudyReport studyReport) {
        return studydao.insert(studyReport);
    }

    public Integer delete(Long reportId) {
        return studydao.delete(reportId);
    }

    //根据UserID查询他的学习记录
    public List<StudyReport> getStudyReport(Long userId) {
        List<StudyReport> reports = studydao.getReportByuserId(userId);
        return reports;
    }

    public StudyReport getReport(long userId, long vCourseId) {
        try {
            StudyReport studyReport=studydao.getReport(userId, vCourseId);
            return studyReport;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
