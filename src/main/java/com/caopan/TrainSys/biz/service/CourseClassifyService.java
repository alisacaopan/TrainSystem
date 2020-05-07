package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.CourseClassifyDao;
import com.caopan.TrainSys.model.CourseClassify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseClassifyService {
    @Autowired
    private CourseClassifyDao classifyDao;

    public int add(CourseClassify classify) {
        return classifyDao.insert(classify);
    }

    public int update(CourseClassify classify) {
        return classifyDao.update(classify);
    }

    public long delete(Integer classifyId) {
        return classifyDao.delete(classifyId);
    }

    public List<CourseClassify> getUserByParentId(Integer parentId) {
        return classifyDao.getClassifyByParentId(parentId);
    }
}

