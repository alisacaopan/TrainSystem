package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.TextCourse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TextCourseDao {

    Integer insert(TextCourse tCourse);

    Integer delete(Long tCourseId);

    List<TextCourse> getTextCourse();

    //获取该课程分类下所有图文课程
    List<TextCourse> getTextCourseByClassifyId(Integer classifyId);

    TextCourse getOnetCourse(Long tCourseId);
}
