package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.Selection;
import com.caopan.TrainSys.model.StudyReport;
import com.caopan.TrainSys.model.Test;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudyDao {

    Integer insert(StudyReport studyReport);

    Integer delete(Long reportId);  //删除学习记录

    //根据学生ID找学习报告
    List<StudyReport> getReportByuserId(long userId);

}

