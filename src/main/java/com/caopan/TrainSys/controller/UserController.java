package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.UserService;
import com.caopan.TrainSys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/insert")
    public int insert(User user) {
        User user1 = new User("Tang","123","6307","1996","185","管理员",1);
        System.out.println(user1.getClassId()+"");
        return userService.add(user1);
    }

    @GetMapping(value = "/update")
    public int update(User user) {
        User user1 = new User("Tang","123","6307","1996","185","管理员",1);
        user1.setId(2);
        return userService.update(user1);
    }

    @GetMapping(value = "/delete")
    public long delete(@RequestParam(value = "id") long id) {
        return userService.delete(id);
    }

    @GetMapping(value = "/getUserByIdcard")
    public User getUserByIdcard(String idcard) {
        String idcard1 = "5456782037367X";
        return userService.getUserByIdcard(idcard1);
    }

    @GetMapping(value = "/getUserByMobile")
    public User getUserByMobile(String mobile) {
        return userService.getUserByMobile("15601623391");
    }

    @GetMapping(value = "/getUserByOpenId")
    public User getUserByOpenId(String openid) {
        return userService.getUserByOpenId(openid);
    }

    @GetMapping(value = "/getUserByClassId")
    public List<User> getUserByClassId(Integer classid) {
        Integer classid1 = 0;
        return userService.getUserByClassId(classid1);
    }


}
