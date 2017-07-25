package com.ylife.thirdplatforminterface.mapper;

import com.ylife.thirdplatforminterface.entity.ThirdPlatformInterfaceEntity;
import org.apache.ibatis.annotations.Param;

public interface ThirdPlatformInterfaceMapper{

    /**
    * 按主键查询ThirdPlatformInterfaceEntity
    * @author henry
    * @date 2017-07-19 10:41:20
    * @param id
    * @return  ThirdPlatformInterfaceEntity
    */
    ThirdPlatformInterfaceEntity queryById(Long id);

    /**
    * 按thirdId查询ThirdPlatformInterfaceEntity
    * @author henry
    * @date 2017-07-19 10:41:20
    * @param thirdId
    * @return  ThirdPlatformInterfaceEntity
    */
    ThirdPlatformInterfaceEntity queryByThirdId(Long thirdId);

    /**
    * 按merchantId查询ThirdPlatformInterfaceEntity
    * @author henry
    * @date 2017-07-19 10:41:20
    * @param merchantId
    * @return  ThirdPlatformInterfaceEntity
    */
    ThirdPlatformInterfaceEntity queryByMerchantId(@Param("merchantId")String merchantId);

    /**
    * 插入ThirdPlatformInterfaceEntity
    * @author henry
    * @date 2017-07-19 10:41:20
    * @param thirdPlatformInterfaceEntity
    * @return int
    */
    int addThirdPlatformInterface(ThirdPlatformInterfaceEntity thirdPlatformInterfaceEntity);

    /**
    * 更新ThirdPlatformInterfaceEntity
    * @author henry
    * @date 2017-07-19 10:41:20
    * @param thirdPlatformInterfaceEntity
    * @return int
    */
    int updateThirdPlatformInterface(ThirdPlatformInterfaceEntity thirdPlatformInterfaceEntity);

    /**
     * 根据店铺Id获取ThirdPlatformInterfaceEntity
     * @param storeId
     * @return
     */
    ThirdPlatformInterfaceEntity queryByStoreId(@Param("storeId")Long storeId);
}
