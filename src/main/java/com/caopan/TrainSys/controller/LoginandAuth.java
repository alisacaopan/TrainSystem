package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.UserService;
import com.caopan.TrainSys.constant.AuthResponseCode;
import com.caopan.TrainSys.model.LoginResult;
import com.caopan.TrainSys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginandAuth {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public LoginResult loginandAuth(@RequestParam( "code") String code) {
        String openId = userService.getOpenId(code);
        LoginResult loginResult = userService.login(openId);
        return loginResult;
    }

    @PostMapping("/authen")
    public LoginResult authen(@RequestParam("openId") String openId,
                            @RequestParam("mobile") String mobile,
                            @RequestParam("idCard") String idCard) {

        System.out.println(openId);
        User user = userService.getUserByMobile(mobile);
        if (user.getIdCard() != null) {
            userService.updateOpenId(openId, mobile);
        }
        LoginResult loginResult = userService.login(openId);
        if (!user.getIdCard().equals(idCard)) {
            loginResult.setCode(AuthResponseCode.USER_AUTH_FAILED);
            loginResult.setDesc(AuthResponseCode.USER_AUTH_FAILED_DESC);
        }

        return loginResult;
    }

}
