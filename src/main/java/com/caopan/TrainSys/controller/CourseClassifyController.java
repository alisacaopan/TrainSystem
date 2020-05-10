package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.CourseClassifyService;
import com.caopan.TrainSys.model.CourseClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseClassifyController {
    @Autowired
    private CourseClassifyService courseclassifyService;

    @PostMapping("/insertClassify")
    public Integer insert(@RequestBody CourseClassify classify) {
        int index = 0;
        try {
            if(courseclassifyService.add(classify) == 1){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            return index;
        }
    }

    @PostMapping(value = "/updateClassify")
    public Integer update(@RequestBody CourseClassify classify) {
        int index = 0;
        try {
            if (courseclassifyService.update(classify) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }
    @GetMapping(value = "/deleteClassify")
    public Integer delete(@RequestParam ("classifyId") Integer classifyId) {
        int index = 0;
        try {
            if(courseclassifyService.delete(classifyId) == 1){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            return index;
        }
    }

    @GetMapping("/getSubclassify")
    public List<CourseClassify> getSubClass(@RequestParam(value = "parentId") Integer id){
        int index = 0;
        List<CourseClassify> classifys = new ArrayList<>();
        try {
            classifys = courseclassifyService.getUserByParentId(id);
            if(classifys.size()>0){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            if (index == 1)
            {
                return courseclassifyService.getUserByParentId(id);
            } else {
                return null;
            }
        }
    }





}
