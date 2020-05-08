package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.VideoCourseService;
import com.caopan.TrainSys.model.VideoCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoCourseController {
    @Autowired
    private VideoCourseService vCourseService;

    @GetMapping(value = "/insertvCourse")
    public Integer insert(VideoCourse vCourse) {
        VideoCourse vCourse1 = new VideoCourse();
        vCourse1.setName("水手课程");
        vCourse1.setAddress("http://media.w3.org/2010/05/sintel/trailer.mp4");
        vCourse1.setIntroduce("ccccccc");
        vCourse1.setClassifyId(7);
        return vCourseService.insert(vCourse1);
    }

    @GetMapping(value = "/updatevCourse")
    public Integer update(VideoCourse vCourse) {
        VideoCourse vCourse1 = new VideoCourse();
        vCourse1.setName("777");
        vCourse1.setAddress("898451s");
        vCourse1.setIntroduce("ccccccc");
        vCourse1.setClassifyId(1);
        vCourse1.setvCourseId((long) 6);
        return vCourseService.update(vCourse1);
    }

    @GetMapping(value = "/deletevCourse")
    public Integer delete(Long vCourseId) {
        return vCourseService.delete((long) 7);
    }

    @GetMapping(value = "/getCourseByClassifyId")
    public List<VideoCourse> getvCourseByClassifyId(@RequestParam("classifyId") Integer classifyId) {
        return vCourseService.getvCourseByClassifyId(classifyId);
    }

    @GetMapping(value = "/getAllvCourses")
    public List<VideoCourse> getAllvCourses() {
        return vCourseService.getAllvCourses();
    }

}
