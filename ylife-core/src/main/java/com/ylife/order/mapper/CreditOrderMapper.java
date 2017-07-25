package com.ylife.order.mapper;


import com.ylife.client.bean.BackOrder;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.CreditOrder;
import com.ylife.order.model.BackOrderRefund;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CreditOrderMapper {

    int deleteByPrimaryKey(Long backOrderId);

    int insert(CreditOrder record);

    int insertSelective(CreditOrder record);

    CreditOrder selectByPrimaryKey(Long id);

    CreditOrder selectBybackOrderId(Long backOrderId);

    Long  selectByOrderId(Long orderId);


    CreditOrder selectByCreditOrderCode(Long creditOrderCode);

    CreditOrder selectByOrderCode(Long orderCode);

    List<CreditOrder> selectByModelSelectiveAndEnterpriseIdAndCreateDate(@Param("model") CreditOrder model,
                                                                         @Param("maxCatalog")Long maxCatalog,
                                                                         @Param("minCatalog")Long minCatalog,
                                                                         @Param("orderCode")Long orderNo,
                                                                         @Param("start") Date start,
                                                                         @Param("end") Date end,
                                                                         @Param("pageable") Pageable pageable);

    int countByModelSelectiveAndEnterpriseIdAndCreateDate(@Param("model") CreditOrder model,
                                                          @Param("maxCatalog")Long maxCatalog,
                                                          @Param("minCatalog")Long minCatalog,
                                                          @Param("orderCode")Long orderNo,
                                                          @Param("start") Date start,
                                                          @Param("end") Date end);

    int updateByPrimaryKeySelective(CreditOrder record);

    int updateByPrimaryKey(CreditOrder record);

    /**
     * 查询退单
     *
     * @return
     */
    List<CreditOrder> selectBackOrderListByCondition();


    /**
     * 根据退单号批量修改退单状态
     *
     * @param list
     * @return
     */
    int updateStatusByBackOrderCode(BackOrder list);

    /**
     * 查询退单账单信息
     * @param backOrderId
     * @return
     * */
    BackOrderRefund selectBackOrderRefundByKey(long backOrderId);

}