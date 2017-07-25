package com.ylife.system.service.impl;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.system.mapper.BusinessTypeHistoryMapper;
import com.ylife.system.model.BusinessTypeHistory;
import com.ylife.system.service.BusinessTypeHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by XuKai on 2016/8/1.
 * BusinessTypeHistoryServiceImpl
 */

@Service
public class BusinessTypeHistoryServiceImpl implements BusinessTypeHistoryService {

    @Resource
    private BusinessTypeHistoryMapper businessTypeHistoryMapper;

    @Override
    public void addHistory(int typeId, String typeName, String operation, String note, String beforeEx, String afterEx, String operator) {
        BusinessTypeHistory model = new BusinessTypeHistory();
        model.setTypeId((long) typeId);
        model.setTypeName(typeName);
        model.setOperation(operation);
        model.setNote(note);
        model.setBeforeEx(beforeEx);
        model.setAfterEx(afterEx);
        model.setOperator(operator);
        model.setDate(new Date());
        businessTypeHistoryMapper.insertSelective(model);
    }

    @Override
    public Page<BusinessTypeHistory> get(Integer typeId, Date startTime, Date endTime, Pageable pageable) {
        List<BusinessTypeHistory> list = businessTypeHistoryMapper.selectByTypeIdAndTime(typeId, startTime, endTime, pageable);
        int totalElements = businessTypeHistoryMapper.countByTypeIdAndTime(typeId, startTime, endTime);
        return new PageImpl<>(pageable, totalElements, list);
    }
}
