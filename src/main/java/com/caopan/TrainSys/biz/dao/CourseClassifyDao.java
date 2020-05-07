package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.CourseClassify;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseClassifyDao {
    Integer insert(CourseClassify classify);

    Integer update(CourseClassify classify);

    Integer delete(Integer classifyId);

    List<CourseClassify> getClassifyByParentId(@Param("parentId") Integer parentId); //查询此分级下所有子类
}
