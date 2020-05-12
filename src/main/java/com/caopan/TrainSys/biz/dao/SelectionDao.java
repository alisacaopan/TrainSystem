package com.caopan.TrainSys.biz.dao;

import com.caopan.TrainSys.model.Selection;
import org.springframework.stereotype.Component;

@Component
public interface SelectionDao {

    Integer insert(Selection selection);
}
