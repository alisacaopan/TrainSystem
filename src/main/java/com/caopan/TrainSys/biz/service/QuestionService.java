package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.QuestionDao;
import com.caopan.TrainSys.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public Long insertQuestion(Question question){
        questionDao.insert(question);
        return question.getQuesId();
    };

    public Integer addQuestionfromexcl(Question question) {
        return questionDao.insert(question);
    }
}