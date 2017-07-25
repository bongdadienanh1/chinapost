package com.ylife.goodsproduct.service;

import com.ylife.chinapost.service.ValetOrderService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.deliveryCode.mapper.DeliveryCodeMapper;
import com.ylife.deliveryCode.model.DeliveryCode;
import com.ylife.deliveryCode.service.DeliveryCodeService;
import com.ylife.goods.service.GoodsProductService;
import com.ylife.goods.service.impl.GoodsProductServiceImpl;
import com.ylife.product.model.GoodsInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.DecimalFormat;

/**
 * Created by InThEnd on 2016/4/11.
 * <p/>
 * TestUcoinGrandService
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestGoodsProductService {

    //商品mapper
    @Resource
    private GoodsProductService goodsProductService;
    @Resource
    private DeliveryCodeMapper deliveryCodeMapper;
    @Resource
    private DeliveryCodeService deliveryCodeService;



    private ValetOrderService valetOrderService;

    @Test
    /**
     * 同步商品信息
     */
    public void saveGoodsProduct() throws Exception {
        goodsProductService.saveGoodsProducts();
    }


    @Test
    public void saveDeliveryCode(){
        for(int i=0;i<100000;i++){
            DeliveryCode item = new DeliveryCode();
            item.setDeliveryStatus("0");
            deliveryCodeMapper.insertSelective(item);
        }
    }

    @Test
    public void selectRandomAbleDeliveryCode(){
        DecimalFormat df5=new DecimalFormat("00000");
        DecimalFormat df3=new DecimalFormat("000");
        String str2=df5.format(deliveryCodeMapper.selectRandomAbleDeliveryCode().getDeliveryId());
        String str=df3.format((int) (Math.random() * 1000));
        System.out.println(str2+str);
    }

    @Test
    public void GetDeliveryCode(){
        String str = deliveryCodeService.getDeliveryCode();
        System.out.print(str);
    }

    @Test
    public void UpdateDeliveryCodeStatus(){
        deliveryCodeService.recycleDeliveryCode("00321680");
    }

 /*   @Test
    public void beginSynchronization(){
        goodsProductService.beginSynchronization("syncGoodsProduct");
    }
*/


}
