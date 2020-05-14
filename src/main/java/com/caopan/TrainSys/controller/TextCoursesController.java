package com.caopan.TrainSys.controller;
import com.caopan.TrainSys.biz.service.TextCourseService;
import com.caopan.TrainSys.model.TextCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TextCoursesController {

    @Autowired
    private TextCourseService textCourseService;

    @GetMapping("/getAllTestCourses")
    public List<TextCourse> getAllTestCourses() {
        return textCourseService.getTextCourses();
    }

}
