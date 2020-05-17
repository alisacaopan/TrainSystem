package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.SignInService;
import com.caopan.TrainSys.biz.service.UserService;
import com.caopan.TrainSys.model.UserSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SignInController {
    @Autowired
    private SignInService signInService;
    @Autowired
    private UserService userService;

    @GetMapping("/getRecored")
    public List<UserSign> getRecored() {
        return signInService.getRecored();
    }

    @GetMapping("/getAllSigIn")
    public List<UserSign> getAllSigIn() {
        return signInService.getAllSigIn();
    }

    @GetMapping("/getAllNotSign")
    public List<UserSign> getAllNotSign() {
        return signInService.getAllNotSign();
    }

    @GetMapping("/signIn")
    public int signIn(@RequestParam("openId") String openId) {
        long userId = userService.getUserByOpenId(openId).getId();
        return signInService.updateByuserId("1", userId);
    }
}
