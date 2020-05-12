package com.caopan.TrainSys.biz.dao;


import com.caopan.TrainSys.model.Question;
import org.springframework.stereotype.Component;

@Component
public interface QuestionDao {

    Integer insert(Question question);
}
