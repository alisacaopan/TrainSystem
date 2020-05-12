package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.SelectionDao;
import com.caopan.TrainSys.model.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelectionService {
    @Autowired
    private SelectionDao selectionDao;

    public Integer addSelectionfromexcl(Selection selection){

        return selectionDao.insert(selection);
    }


}
