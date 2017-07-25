package com.ylife.inventory;

/**
 * Created by Administrator on 2016/5/31.
 */

import com.alibaba.fastjson.JSON;
import com.ylife.chinapost.service.InventoryManageService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.inventory.mapper.InventoryBillMapper;
import com.ylife.inventory.mapper.InventoryMapper;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.model.InventoryBill;
import com.ylife.inventory.model.InventoryKey;
import com.ylife.inventory.service.InventoryHistoryService;
import com.ylife.inventory.service.InventoryService;
import com.ylife.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class testInventory {

    @Resource
    private InventoryManageService inventoryManageService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private InventoryBillMapper inventoryBillMapper;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private InventoryMapper inventoryMapper;
    @Resource
    private InventoryHistoryService inventoryHistoryService;

    @Test
    public void testInsertByBatch(){
        String ds="2016-11-21 00:00:00";
        Date date=DateUtil.fromString(ds, "yyyy-MM-dd hh:mm:ss");
        inventoryHistoryService.insertByBatch();
    }


    @Test
    public  void testGetGoods(){
        List<Long> list=new LinkedList<>();
        list.add(4224l);
        list.add(4289l);

        List<InventoryGoodsResult> inventoryGoodsResults=inventoryMapper.selectGoodsAndAvailable(706l,list);
        System.out.println();
        System.out.println(inventoryGoodsResults);
    }



    @Test
    public void testInventoryService() {
        InventoryKey key = new InventoryKey(1l, 3014l);
        long enterpriseId = 1;

        InventoryGoodsResult inventoryGoodsResult = inventoryManageService.getInventoryGoodsResultByPrimarykey(3014);
        System.out.println(inventoryGoodsResult.getGoodsInfoName());
    }

    @Test
    public void testCatalog() {
        EnterpriseFunction enterpriseFunction = enterpriseFunctionMapper.selectByPrimaryKey(1l);
        Long maxCatalog = enterpriseFunction.getMaxCatalog();
        Long minCatalog = enterpriseFunction.getMinCatalog();

        System.out.println(maxCatalog);
        System.out.println(minCatalog);
    }


    @Test
    public void testInventoryBillMapper() {
        Long billId = 3l;
        InventoryBill inventoryBill = inventoryBillMapper.selectBillDetailByPrimaryKey(billId);
        System.out.println(new JsonResponseBean(inventoryBill).toJson());

        Long code = 1606200335622000l;
        //InventoryBill inventoryBillCode = inventoryBillMapper.selectBillDetailByCode(code);
        //System.out.println(new JsonResponseBean(inventoryBillCode).toJson());
    }


    @Test
    public void testGetByInventory() {
        EnterpriseFunction enterpriseFunction = enterpriseFunctionMapper.selectByPrimaryKey(1l);
        Long maxCatalog = enterpriseFunction.getMaxCatalog();
        Long minCatalog = enterpriseFunction.getMinCatalog();

        List<InventoryGoodsResult> inventoryGoodsResultList = inventoryMapper.selectTotalInventoryGoodsByCatage(minCatalog, maxCatalog, null, null, null, null, null);

        System.out.println(new JsonResponseBean(inventoryGoodsResultList).toJson());

    }
}
