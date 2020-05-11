package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.TestService;
import com.caopan.TrainSys.biz.service.UserService;
import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
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

    /**
     *
     * @param openId
     * @param testArray
     * @param vCourseId
     * @return 1表示记录成功 0表示没有记录成功
     */
    @PostMapping("/testrecord")
    public Long testrecord(@RequestParam("openId") String openId,
                                @RequestParam("testArray") long[][] testArray,
                                @RequestParam("vCourseId") Long  vCourseId) {
        float Grade = 0;
        int index = 0;
        Long testId = (long)0;
        Long userId = userService.getUserByOpenId(openId).getId();
        try {
            String testRecord = "";
            for (int i = 0; i<testArray.length/2; i++ ){
                if (testArray[2*i+1][0]==(long)1){
                    Grade = Grade + 1;
                }
                testRecord  = Arrays.deepToString(testArray);
            }
            Grade = Grade / testArray.length;
            Grade = Grade * 2;
            Test test = new Test();
            test.setUserId(userId);
            test.setvCourseId(vCourseId);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            Date date = new Date();
            test.setTestTime(df.format(date));
            test.setGrade(Grade);
            test.setTestRecord(testRecord);
            if(testService.insert(test)==1){
                index = 1;
            }
            testId = test.getTestId();
        } catch (Exception e) {
        } finally {
            if(index == 1){
                return testId;
            } else {
                return (long)0;
            }

        }
    }

    @GetMapping("/getGradeBytestId")
    public float getGradeBytestId(@RequestParam("testId") long testId) {
        return testService.getTestBytestId(testId).getGrade();
    }
}
