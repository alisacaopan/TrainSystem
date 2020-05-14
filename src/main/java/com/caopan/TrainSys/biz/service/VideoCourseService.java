package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.TestDao;
import com.caopan.TrainSys.biz.dao.VideoCourseDao;
import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.Selection;
import com.caopan.TrainSys.model.VideoCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoCourseService {
    @Autowired
    private VideoCourseDao vcourseDao;

    //名称重复贼不插入
    public Integer insert(VideoCourse vCourse) {
        List<VideoCourse> vcoures = vcourseDao.getvCourse();
        for (int i = 0; i < vcoures.size(); i++) {
            if (vCourse.getName().equals(vcoures.get(i).getName())) {
                return 0;
            }
        }
        vcourseDao.insert(vCourse);
        return 1;
    }

    public Integer update(VideoCourse vCourse) {
        return vcourseDao.update(vCourse);
    }

    public Integer delete(Long vCourseId) {
        return vcourseDao.delete(vCourseId);
    }

    //根据分级取得改分级下所有课程
    public List<VideoCourse> getvCourseByClassifyId(Integer classifyId) {
        return vcourseDao.getvCourseByClassifyId(classifyId);
    }

    public List<VideoCourse> getAllvCourses() {
        return vcourseDao.getvCourse();
    }

    //获得单个视频
    public  VideoCourse getOneCourse(Long vCourseId){return vcourseDao.getOneCourse(vCourseId);}
}
