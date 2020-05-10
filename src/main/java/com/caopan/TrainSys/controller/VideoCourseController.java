package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.TestService;
import com.caopan.TrainSys.biz.service.VideoCourseService;
import com.caopan.TrainSys.model.VideoCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VideoCourseController {
    @Autowired
    private VideoCourseService vCourseService;

    @PostMapping(value = "/insertvCourse")
    public Integer insert(@RequestBody VideoCourse vCourse) {
        int index = 0;
        try {
            if (vCourseService.insert(vCourse) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @PostMapping(value = "/updatevCourse")
    public Integer update(@RequestBody VideoCourse vCourse) {
        int index = 0;
        try {
            if (vCourseService.update(vCourse) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @GetMapping(value = "/deletevCourse")
    public Integer delete(@RequestParam("vCourseId") Long vCourseId) {
        int index = 0;
        try {
            if (vCourseService.delete(vCourseId) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @GetMapping(value = "/getCourseByClassifyId")
    public List<VideoCourse> getvCourseByClassifyId(@RequestParam("classifyId") Integer classifyId) {
        int index = 0;
        try {
            if (vCourseService.getvCourseByClassifyId(classifyId).size() > 0) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return vCourseService.getvCourseByClassifyId(classifyId);
            } else {
                return null;
            }
        }
    }

    @GetMapping(value = "/getAllvCourses")
    public List<VideoCourse> getAllvCourses() {
        return vCourseService.getAllvCourses();
    }
}
