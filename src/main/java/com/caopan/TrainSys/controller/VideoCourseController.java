package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.TestService;
import com.caopan.TrainSys.biz.service.VideoCourseService;
import com.caopan.TrainSys.model.VideoCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VideoCourseController {
    @Autowired
    private VideoCourseService vCourseService;

    @GetMapping(value = "/insertvCourse")
    public Integer insert(VideoCourse vCourse) {
        VideoCourse vCourse1 = new VideoCourse();
        vCourse1.setName("123");
        vCourse1.setAddress("456789");
        vCourse1.setIntroduce("bbbbbbb");
        vCourse1.setClassifyId(1);
        return vCourseService.insert(vCourse1);
    }

    @GetMapping(value = "/updatevCourse")
    public Integer update(VideoCourse vCourse) {
        VideoCourse vCourse1 = new VideoCourse();
        vCourse1.setName("777");
        vCourse1.setAddress("898451s");
        vCourse1.setIntroduce("ccccccc");
        vCourse1.setClassifyId(1);
        vCourse1.setvCourseId((long)6);
        return vCourseService.update(vCourse1);
    }

    @GetMapping(value = "/deletevCourse")
    public Integer delete(Long vCourseId) {
        return vCourseService.delete((long)7);
    }
    @GetMapping(value = "/getCourseByClassifyId")
    public List<VideoCourse> getvCourseByClassifyId(Integer classifyId) {
        return vCourseService.getvCourseByClassifyId(1);
    }


}
