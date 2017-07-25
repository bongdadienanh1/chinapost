package com.ylife.form.service.impl;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.form.model.InventoryChangeHistoryForm;
import com.ylife.form.service.InventoryChangeFormService;
import com.ylife.inventory.mapper.InventoryChangeHistoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by XiaoBiaoGe on 2016/11/23.
 */
@Service
public class InventoryChangeFormServiceImpl implements InventoryChangeFormService {

    @Resource
    private InventoryChangeHistoryMapper inventoryChangeHistoryMapper;

    @Override
    public Page<InventoryChangeHistoryForm> getHistoryChangeForm(Long maxCatalog, Long minCatalog, Date start, Date end, String goodsInfoName, String goodsInfoItemNo, Pageable pageable) {
        List<InventoryChangeHistoryForm> list=inventoryChangeHistoryMapper.selectInventoryChangeByEnterprise(maxCatalog,minCatalog,start,end,goodsInfoName,goodsInfoItemNo,pageable);
        int totalElements=inventoryChangeHistoryMapper.countInventoryChangeByEnterprise(maxCatalog,minCatalog,start,end,goodsInfoName,goodsInfoItemNo);
        return new PageImpl<>(pageable,totalElements,list);
    }
}
