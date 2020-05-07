package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.TestDao;
import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TestService {
    @Autowired private TestDao testDao;

    public long delete(long testId){
        return testDao.delete(testId);
    }

    //随机获得x道题的题号和状态,状态默认置0
    public List getQuestionId() {
        List<Question> questions = testDao.getQuestionRandly();
        List quesAndStatus = new ArrayList();
        for (int i = 0; i < questions.size(); i++) {
            Long[] question = new Long[2];
            question[0] = questions.get(i).getQuesId();
            question[1] = (long) 0;
            quesAndStatus.add(question);
        }
        return quesAndStatus;
    }

    //根据题目id获得题干和选项,String数组的第一个为题干，后面的都为选项
    public String[] getContent(Long quesId) {
        Question question = testDao.getQuesByquesId(quesId);
        String quesContent = question.getQuesContent(); //获取题干
        List<Selection> selections = testDao.getSelectionByquesId(question.getQuesId());
        String[] Content = new String[selections.size()+1];
        Content[0] = quesContent;
        //获取选项
        for (int i = 1; i<selections.size()+1; i++){
            Content[i] = selections.get(i-1).getSelectionContent();
        }
        return Content;
    }

    //根据题目id得到答案,进行单个答案比较，返回值为分数
    public Integer getAnwser(Long quesId,int[] anwser) {
        //答案数组
        List<Selection> selections = testDao.getSelectionByquesId(quesId);
        //查找正确答案的个数
        int length = 0;
        for (int j = 0; j<selections.size();j++){
            if(selections.get(j).getIsRight()==1){length = length+1;}
        }
        //获得正确答案
        int[] rightAnwser = new int[length];
        int index = 0;
        for (int i = 0; i<selections.size(); i++) {
            if (selections.get(i).getIsRight()==1){
                rightAnwser[index] = i+1;
                index ++;
            }
        }
        //将正确答案和传入的答案比较,答对了得一分，错了没有分
        if (Arrays.equals(rightAnwser,anwser)){
            return 1;
        } else {
            return 0;
        }
    }

    //传入所有题目的答案，一次性比较得到分数,allQuesId每个元素为Long,AllAnwser每个元素为int数组
    public Integer getAllAnwser(List<Long> allQuesId,List<int[]> allAnwser) {
        int grade = 0;  //总分
        for (int i = 0; i < allQuesId.size(); i++) {
            List<Selection> selections = testDao.getSelectionByquesId(allQuesId.get(i));
            //查找正确答案的个数
            int length = 0;
            for (int j = 0; j < selections.size(); j++) {
                if (selections.get(j).getIsRight() == 1) {
                    length = length + 1;
                }
            }
            //获得正确答案
            int[] rightAnwser = new int[length];
            int index = 0;
            for (int j = 0; j < selections.size(); j++) {
                if (selections.get(j).getIsRight() == 1) {
                    rightAnwser[index] = j + 1;
                    index++;
                }
            }
            //将正确答案和传入的答案比较,答对了加一分
            if (Arrays.equals(rightAnwser, allAnwser.get(i))) {
                grade = grade + 1;
            }
        }
        return grade;
    }
}