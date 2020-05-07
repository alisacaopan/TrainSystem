package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping(value = "/deleteTest")
    public long delete(long id) {
        return testService.delete(id);
    }

    @GetMapping("/getAnwser")
    public Integer getAnwser(){
        int[]a = {3};
        return testService.getAnwser((long)1,a);
    }

    @GetMapping("/getAllAnwser")
    public Integer getAllAnwser(){
        List<Long> quesId = new ArrayList();
        quesId.add((long)1);
        quesId.add((long)2);
        List<int[]>anwsers = new ArrayList();
        int[] b = {3};
        int[] c = {1};
        anwsers.add(b);
        anwsers.add(c);
        return testService.getAllAnwser(quesId,anwsers);
    }

}
