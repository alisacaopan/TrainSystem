package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.TextCourse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TextCourseDao {
    List<TextCourse> getTextCourse();
}
