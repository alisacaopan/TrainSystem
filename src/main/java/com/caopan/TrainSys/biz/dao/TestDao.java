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

    //随机生成试卷，返回2道题的题号和题干
    List<Question> getQuestionRandly(int vCourseId);

    //根据题目号找题
    Question getQuesByquesId(long quesId);

    //根据题目号找所有选项
    List<Selection> getSelectionByquesId(long quesId);

    public Test getTestBytestId(long testId);

    public void getLastTestId();
}

