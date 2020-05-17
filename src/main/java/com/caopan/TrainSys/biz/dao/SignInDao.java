package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.SignIn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SignInDao {

    int insert(SignIn signIn);

    List<SignIn> getAllRecored();

    List<SignIn> getAllSigIn();

    List<SignIn> getAllNotSign();

    Integer updateByuserId(String signStatus, Long userId);

    Integer deleteByUserId(long userId);
}
