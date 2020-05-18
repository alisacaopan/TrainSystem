package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.SignInDao;
import com.caopan.TrainSys.model.SignIn;
import com.caopan.TrainSys.model.User;
import com.caopan.TrainSys.model.UserSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignInService {
    @Autowired
    private SignInDao signInDao;
    @Autowired
    private UserService userService;

    public Integer SignInSetZero(String signStatus){return signInDao.SignInSetZero(signStatus);}

    public List<UserSign> getRecored() {
        List<SignIn> signIns = signInDao.getAllRecored();
        return getresult(signIns);

    }

    public List<UserSign> getAllSigIn() {
        List<SignIn> signIns = signInDao.getAllSigIn();
        return getresult(signIns);

    }

    public List<UserSign> getAllNotSign() {
        List<SignIn> signIns = signInDao.getAllNotSign();
        return getresult(signIns);

    }

    public int updateByuserId(String signStatus, Long userId) {
        return signInDao.updateByuserId(signStatus, userId);

    }

    public List<UserSign> getresult(List<SignIn> signIns) {
        List<UserSign> userSignList = new ArrayList<>();
        for (SignIn s : signIns
        ) {

            UserSign userSign = new UserSign();
            userSign.setSignInId(s.getSignInId());
            userSign.setSignInTime(s.getSignInTime());
            userSign.setSignStatus(s.getSignStatus());
            userSign.setUserId(s.getUserId());
            User user = userService.getUserById(s.getUserId());
            userSign.setUserName(user.getName());
            userSign.setOpenId(user.getOpenId());
            userSignList.add(userSign);
        }

        return userSignList;
    }


}
