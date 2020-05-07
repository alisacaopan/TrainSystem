package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.Selection;
import com.caopan.TrainSys.model.VideoCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoCourseDao {
    Integer insert(VideoCourse course);

    Integer update(VideoCourse course);

    Integer delete(Long vCourseId);

    //取得所有课程
    List<VideoCourse> getvCourse();

    List<VideoCourse> getvCourseByClassifyId(@Param("classifyId") Integer classifyId); //查询此分类所有课程
}

