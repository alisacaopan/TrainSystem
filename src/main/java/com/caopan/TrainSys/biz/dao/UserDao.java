package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.User;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    Integer insert(User user);

    User getUserByIdcard(@Param("idCard") String idCard);

    User getUserByMobile(@Param("mobile") String mobile);

    User getUserByOpenId(@Param("openId") String openId);

    Integer update(User user);

    Integer delete(long id);

    List<User> getUserByClassId(@Param("classId") Integer classId);

    Integer updateOpenId(@Param("openId") String openId, @Param("mobile") String mobile);

    void insertStudentFromexcl(User user);

    List<User> findAllStudents();

    List<User> getUserByMobileAndIdCard(String mobile,String idCard);
}

