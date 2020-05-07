package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.CourseClassifyService;
import com.caopan.TrainSys.model.CourseClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseClassifyController {
    @Autowired
    private CourseClassifyService courseclassifyService;

    @GetMapping("/insertClassify")
    public int insert(CourseClassify classify) {
        CourseClassify classify1 = new CourseClassify();
        classify1.setName("医疗");
        classify1.setLevel(1);
        classify1.setParentId(0);
        return courseclassifyService.add(classify1);
    }

    @GetMapping(value = "/updateClassify")
    public int update(CourseClassify classify) {
        CourseClassify classify1 = new CourseClassify();
        classify1.setClassifyId(6);
        classify1.setName("水手");
        classify1.setLevel(1);
        classify1.setParentId(0);
        return courseclassifyService.update(classify1);
    }

    @GetMapping(value = "/deleteClassify")
    public long delete(Integer id) {
        return courseclassifyService.delete(id);
    }

    @GetMapping("/getSubclassify")
    public List<CourseClassify> getSubClass(@RequestParam(value = "parentId") Integer id){
        return courseclassifyService.getUserByParentId(id);
    }

}
