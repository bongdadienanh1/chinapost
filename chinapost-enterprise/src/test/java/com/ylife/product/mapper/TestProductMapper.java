package com.ylife.product.mapper;


import com.ylife.chinapost.service.ValetOrderService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.order.mapper.CreditOrderMapper;
import com.ylife.order.model.CreditOrder;
import com.ylife.product.model.GoodsInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestProductMapper {

    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private GoodsImagesMapper goodsImagesMapper;
    @Resource
    ValetOrderService valetOrderService;




    @Test
    public void testSelectByModelSelectiveAndCreateDate() {
        GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(4895l);
        List<GoodsManagerResult> goodsManagerResults=goodsInfoMapper.selectNetWorkGoods(706l,4895l,null,null,null,null);


        System.out.print(new JsonResponseBean(goodsManagerResults.get(0)).toJson());
    }

    @Test
    public void getValetGoodsByGoodsInfoId(){
        GoodsInfo goodsInfo=valetOrderService.getValetGoodsByGoodsInfoId(4597l);
        System.out.print(new JsonResponseBean(goodsInfo).toJson());
    }

}