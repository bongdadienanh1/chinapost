package com.ylife.thirdplatform.mapper;

import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ThirdPlatformMapper{
    /**
    * 查询全部ThirdPlatformEntity 不带分页
    * @author henry
    * @date 2017-06-30 08:52:14
    * @param thirdPlatformEntity
    * @return List<ThirdPlatformEntity>
    */
    List<ThirdPlatformEntity> queryAllThirdPlatform(ThirdPlatformEntity thirdPlatformEntity);

    /**
    * 查询全部ThirdPlatformEntity 带分页
    * @author henry
    * @date 2017-06-30 08:52:14
    * @param paramMap
    * @return List<Object>
    */
    List<ThirdPlatformEntity> queryListForPageThirdPlatform(Map<String,Object> paramMap);

    /**
    * 查询总数ThirdPlatformEntity
    * @author henry
    * @date 2017-06-30 08:52:14
    * @param thirdPlatformEntity
    * @return
    */
    int queryCountThirdPlatform(ThirdPlatformEntity thirdPlatformEntity);

    /**
    * 按主键查询ThirdPlatformEntity
    * @author henry
    * @date 2017-06-30 08:52:14
    * @param id
    * @return  ThirdPlatformEntity
    */
    ThirdPlatformEntity queryById(Long id);

    /**
    *
    * @Title: queryByIds
    * @Description: 根据主键id批量查询数据
    * @param ids
    * @return
    * @throws
    */
    List<ThirdPlatformEntity> queryByIds(List<String> ids);

    /**
    * 插入ThirdPlatformEntity
    * @author henry
    * @date 2017-06-30 08:52:14
    * @param thirdPlatformEntity
    * @return int
    */
    int addThirdPlatform(ThirdPlatformEntity thirdPlatformEntity);

    /**
    * 更新ThirdPlatformEntity
    * @author henry
    * @date 2017-06-30 08:52:14
    * @param paramMap
    * @return int
    */
    int updateThirdPlatform(Map<String,Object> paramMap);

    /**
     * 根据用户名密码获取用户信息
     * @param thirdPlatformEntity
     * @return ThirdPlatformEntity
     * */
    ThirdPlatformEntity queryByUserNameAndPwd(ThirdPlatformEntity thirdPlatformEntity);

    /**
     * 根据storeId获取ThirdPlatformEntity
     * @param storeId
     * @return
     */
    ThirdPlatformEntity queryByStoreId(@Param("storeId")Long storeId);
}