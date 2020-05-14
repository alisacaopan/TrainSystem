package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.TextCourseDao;
import com.caopan.TrainSys.model.TextCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextCourseService {
    @Autowired
    private TextCourseDao textCourseDao;

    public List<TextCourse> getTextCourses() {
        return textCourseDao.getTextCourse();
    }
}
