package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.QuestionDao;
import com.caopan.TrainSys.biz.dao.SelectionDao;
import com.caopan.TrainSys.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private SelectionDao selectionDao;

    public Long insertQuestion(Question question){
        questionDao.insert(question);
        return question.getQuesId();
    };

    public Integer addQuestionfromexcl(Question question) {
        return questionDao.insert(question);
    }

    public Integer deleteByClassifyId(Integer classifyId){
        List<Question> questions = questionDao.getQuestionByClassifyId(classifyId);
        for (int i = 0; i<questions.size();i++){
            selectionDao.delete(questions.get(i).getQuesId());
        }
        return questionDao.delete(classifyId);
    }
}