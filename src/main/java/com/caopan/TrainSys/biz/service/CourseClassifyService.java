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

    public Integer add(CourseClassify classify) {
        return classifyDao.insert(classify);
    }

    public Integer update(CourseClassify classify) {
        return classifyDao.update(classify);
    }

    public Integer delete(Integer classifyId) {
        return classifyDao.delete(classifyId);
    }

    //查询分级下的所有子类
    public List<CourseClassify> getUserByParentId(Integer parentId) {
        return classifyDao.getClassifyByParentId(parentId);
    }

    //查询分级下的所有子类的数目
    public Integer getClassNumByParentId(Integer parentId) {
        return classifyDao.getClassifyByParentId(parentId).size();
    }


}

