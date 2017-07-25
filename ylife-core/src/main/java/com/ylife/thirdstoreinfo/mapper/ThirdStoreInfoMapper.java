package com.ylife.thirdstoreinfo.mapper;

import java.util.List;
import java.util.Map;

import com.ylife.thirdstoreinfo.entity.ThirdStoreInfoEntity;

public interface ThirdStoreInfoMapper{
    /**
    * 查询全部ThirdStoreInfoEntity 不带分页
    * @author henry
    * @date 2017-06-30 13:29:26
    * @param thirdStoreInfoEntity
    * @return List<ThirdStoreInfoEntity>
    */
    List<ThirdStoreInfoEntity> queryAllThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity);

    /**
    * 查询全部ThirdStoreInfoEntity 带分页
    * @author henry
    * @date 2017-06-30 13:29:26
    * @param paramMap
    * @return List<Object>
    */
    List<Object> queryListForPageThirdStoreInfo(Map<String, Object> paramMap);

    /**
    * 查询总数ThirdStoreInfoEntity
    * @author henry
    * @date 2017-06-30 13:29:26
    * @param thirdStoreInfoEntity
    * @return
    */
    int queryCountThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity);

    /**
    * 按主键查询ThirdStoreInfoEntity
    * @author henry
    * @date 2017-06-30 13:29:26
    * @param storeId
    * @return  ThirdStoreInfoEntity
    */
    ThirdStoreInfoEntity queryById(Long storeId);

    /**
    *
    * @Title: queryByIds
    * @Description: 根据主键id批量查询数据
    * @param ids
    * @return
    * @throws
    */
    List<ThirdStoreInfoEntity> queryByIds(List<String> ids);

    /**
    * 插入ThirdStoreInfoEntity
    * @author henry
    * @date 2017-06-30 13:29:26
    * @param thirdStoreInfoEntity
    * @return int
    */
    int addThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity);

    /**
    * 更新ThirdStoreInfoEntity
    * @author henry
    * @date 2017-06-30 13:29:26
    * @param thirdStoreInfoEntity
    * @return int
    */
    int updateThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity);
}
