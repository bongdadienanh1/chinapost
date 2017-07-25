package com.ylife.chinapost.boss.service.impl;


import com.ylife.chinapost.boss.service.InventoryManageService;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.InventoryMapper;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XuKai on 2016/4/21.
 * InventoryManageServiceImpl
 */
@Service
public class InventoryManageServiceImpl implements InventoryManageService {

    @Resource
    private InventoryMapper inventoryMapper;

    public Page<InventoryGoodsResult> getInventoryGoodsResultBYEnterpriseId(Long enterpriseId, String goodsName, Integer goodsInfoType, String goodsNumber, Long brandId, Long thirdId,String goodsInfoAdded,Integer onlineShow, Pageable pageable) {
        List<InventoryGoodsResult> inventoryGoodsResults = inventoryMapper.selectInventoryGoods(enterpriseId, goodsName, goodsNumber, brandId, thirdId, goodsInfoType,goodsInfoAdded,onlineShow, pageable);
        int totalElements = inventoryMapper.countByEnterpriseId(enterpriseId, goodsName, goodsNumber, brandId, thirdId, goodsInfoType,goodsInfoAdded,onlineShow);
        return new PageImpl<InventoryGoodsResult>(pageable, totalElements, inventoryGoodsResults);
    }
}




