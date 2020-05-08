package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping(value = "/deleteTest")
    public Integer deleteTest(@RequestParam(value = "testId") Long testId) {
        int index = 0;
        try {
            if(testService.delete(testId) == 1){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            return index;
        }
    }

    @PostMapping("/getAnwser")
    public Integer getAnwser(@RequestParam ("quesId")Long quesId,@RequestParam("anwser") int[] anwser){
        int index = 0;
        try {
            //如果答案是正确的 返回1，错误的返回0 ，异常也返回0
            if(testService.getAnwser(quesId,anwser) == 1){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            return index;
        }
    }

    @PostMapping("/getAllAnwser")
    public Integer getAllAnwser(@RequestParam ("quesId")List<Long> quesId,@RequestParam("anwsers") List<int[]> anwsers){
        int index = 0;
        int Grade = 0;
        try {
            Grade = testService.getAllAnwser(quesId,anwsers);
            //如果分数不是0分,返回分数
            if(Grade > 0){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            if (index == 1){
                return Grade;
            } else {
                return 0;
            }

        }

    }

}
