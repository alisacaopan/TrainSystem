package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.TestService;
import com.caopan.TrainSys.biz.service.UserService;
import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;
    private UserService userService;

    @PostMapping(value = "/deleteTest")
    public Integer deleteTest(@RequestParam(value = "testId") Long testId) {
        int index = 0;
        try {
            if (testService.delete(testId) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @PostMapping("/getAnwser")
    public Integer getAnwser(@RequestParam("quesId") Long quesId, @RequestParam("anwser") int[] anwser) {
        int index = 0;
        try {
            //如果答案是正确的 返回1，错误的返回0 ，异常也返回0
            if (testService.getAnwser(quesId, anwser) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @PostMapping("/getAllAnwser")
    public Integer getAllAnwser(@RequestParam("quesId") List<Long> quesId,
                                @RequestParam("anwsers") List<int[]> anwsers,
                                @RequestParam("userId") Long userId) {
        int index = 0;
        int Grade = 0;
        try {
            Grade = testService.getAllAnwser(quesId, anwsers, userId);
            //如果分数不是0分,返回分数
            if (Grade > 0) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return Grade;
            } else {
                return 0;
            }
        }
    }

    @GetMapping("/startTest")
    public List startTest(@RequestParam("vCourseId") int vCourseId) {
        return testService.getQuestionId(vCourseId);
    }

    @GetMapping("/getQuesContent")
    public List<Object> getQuesContent(@RequestParam("quesId") Long quesId) {
        return testService.getContent(quesId);
    }

    @GetMapping("/getQuestionType")
    public String getQuestionType(@RequestParam("quesId") Long quesId){
        Question question=testService.getQuestionById(quesId);
        return question.getQuesType();
    }

    @PostMapping("/getGradeByOpenId")
    public Integer getAllAnwser(@RequestParam("openId") String openId,
                                @RequestParam("testArray") List<long[]> testArray,
                                @RequestParam(" vCourseId") Long  vCourseId) {
        int index = 0;
        int Grade = 0;
        Long userId = userService.getUserByOpenId(openId).getId();
        try {
            for (int i = 0; i<testArray.size(); i++ ){
                if (testArray.get(1).equals((long)1)){
                    Grade = Grade + 1;
                }
            }
            Test test = new Test();
            test.setUserId(userId);
            test.setvCourseId(vCourseId);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            Date date = new Date();
            test.setTestTime(df.format(date));
            test.setGrade(Grade);
            index = testService.insert(test);
            //如果分数不是0分,返回分数
            if (Grade > 0) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return Grade;
            } else {
                return 0;
            }
        }
    }


}
