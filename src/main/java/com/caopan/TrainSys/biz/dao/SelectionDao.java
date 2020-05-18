package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.Selection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SelectionDao {

    Integer insert(Selection selection);

    Integer delete(Long quesId);

    //根据题目号找所有选项
    List<Selection> getSelectionByquesId(long quesId);

}
