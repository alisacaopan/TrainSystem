package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.CourseClassify;
import com.caopan.TrainSys.model.VideoCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseClassifyDao {
    Integer insert(CourseClassify classify);

    Integer update(CourseClassify classify);

    Integer delete(Integer classifyId);

    CourseClassify getCourClassify(Integer classifyId);

    List<CourseClassify> getClassifyByParentId(@Param("parentId") Integer parentId); //查询此分级下所有子类

    List<CourseClassify> getAllClassify();
}
