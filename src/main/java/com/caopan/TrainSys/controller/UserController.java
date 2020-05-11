package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.UserService;
import com.caopan.TrainSys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/insert")
    public Integer insert(@RequestBody User user) {
        int index = 0;
        try {
            if(userService.add(user) == 1){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            return index;
        }

    }

    @PostMapping(value = "/update")
    public Integer update(@RequestBody User user) {
        int index = 0;
        try {
            if(userService.update(user) == 1){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            return index;
        }
    }

    @GetMapping(value = "/delete")
    public long delete(@RequestParam(value = "id") long id) {
        int index = 0;
        try {
            if(userService.delete(id) == 1){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            return index;
        }
    }

    @GetMapping(value = "/getUserByIdcard")
    public User getUserByIdcard(@RequestParam("idCard") String idcard) {
        int index = 0;
        try {
            if(userService.getUserByIdcard(idcard)!=null){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            if (index == 1) {
                return userService.getUserByIdcard(idcard);
            } else {
                return null;
            }
        }
    }

    @GetMapping(value = "/getUserByMobile")
    public User getUserByMobile(@RequestParam("mobile") String mobile) {
        int index = 0;
        try {
            if(userService.getUserByMobile(mobile)!=null){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            if (index == 1) {
                return userService.getUserByMobile(mobile);
            } else {
                return null;
            }
        }
    }

    @GetMapping(value = "/getUserByOpenId")
    public User getUserByOpenId(@RequestParam("openId") String openId) {
        int index = 0;
        try {
            if(userService.getUserByOpenId(openId).getId()>0){
                index = 1;
            } else {
               index = 0;
            }
        }catch (Exception e){
        }finally {
            if (index == 1) {
                return userService.getUserByOpenId(openId);
            } else {
               return null;
            }
        }
    }

    @GetMapping(value = "/getUserByClassId")
    public List<User> getUserByClassId(@RequestParam("classId") Integer classid) {
        int index = 0;
        try {
            if(userService.getUserByClassId(classid).size()>0){
                index = 1;
            } else {
                index = 0;
            }
        }catch (Exception e){
        }finally {
            if (index == 1) {
                return userService.getUserByClassId(classid);
            } else {
                return null;
            }
        }
    }


}
