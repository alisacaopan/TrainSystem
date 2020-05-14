package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.Selection;
import com.caopan.TrainSys.model.Test;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestDao {

    Integer insert(Test test);

    Integer delete(Long testId);  //删除测试记录

    List<Test> getTestByUserId(Long userId);




    public Test getTestBytestId(long testId);

}

