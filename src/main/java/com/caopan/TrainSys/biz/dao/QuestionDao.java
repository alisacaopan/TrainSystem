package com.caopan.TrainSys.biz.dao;


import com.caopan.TrainSys.model.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionDao {

    Integer insert(Question question);

    Integer delete(Integer classifyId);

    //根据分类找题
    List<Question> getQuestionByClassifyId(int classifyId);
    //随机生成试卷，返回2道题的题号和题干
    List<Question> getQuestionRandly(int vCourseId);
    //根据题目号找题
    Question getQuesByquesId(long quesId);
}
