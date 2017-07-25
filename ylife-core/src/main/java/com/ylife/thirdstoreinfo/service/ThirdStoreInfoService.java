package com.ylife.thirdstoreinfo.service;

import java.util.List;
import com.ylife.data.page.Pageable;
import com.ylife.thirdstoreinfo.entity.ThirdStoreInfoEntity;

public interface ThirdStoreInfoService {

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
	* @param thirdStoreInfoEntity
	* @param pb
	* @return List<ThirdStoreInfoEntity>
	*/
	List<Object> queryListForPageThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity, Pageable pb);

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
	* @Title: queryByIds
	* @Description: 根据主键id批量查询数据
	* @author henry
	* @date 2017-06-30 13:29:26
	* @param ids
	* @return
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

