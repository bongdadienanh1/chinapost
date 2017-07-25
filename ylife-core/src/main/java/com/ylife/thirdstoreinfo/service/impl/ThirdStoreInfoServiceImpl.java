package com.ylife.thirdstoreinfo.service.impl;


import com.ylife.data.page.Pageable;
import com.ylife.thirdstoreinfo.service.ThirdStoreInfoService;
import com.ylife.utils.MapUtil;
import com.ylife.utils.MyLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.ylife.thirdstoreinfo.mapper.ThirdStoreInfoMapper;
import com.ylife.thirdstoreinfo.entity.ThirdStoreInfoEntity;

@Service(value="thirdStoreInfoService")
public class ThirdStoreInfoServiceImpl implements ThirdStoreInfoService {
	/**
	* 日志
	* */
	public static final MyLogger LOGGER = new MyLogger(ThirdStoreInfoServiceImpl.class);

	@Resource
	private ThirdStoreInfoMapper thirdStoreInfoMapper;

	/**
	*
	* @author henry
	* @date 2017-06-30 13:29:26
	* @see ThirdStoreInfoService#queryAllThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity)
	* @param thirdStoreInfoEntity
	* @return List<ThirdStoreInfoEntity>
    */
    public List<ThirdStoreInfoEntity> queryAllThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity) {
    	return thirdStoreInfoMapper.queryAllThirdStoreInfo(thirdStoreInfoEntity);
    }
    /**
    *
    * @author henry
    * @date 2017-06-30 13:29:26
    * @see ThirdStoreInfoService#queryAllThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity)
    * @param thirdStoreInfoEntity
    * @return List<ThirdStoreInfoEntity>
	*/
	public List<Object> queryListForPageThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity, Pageable pb) {
        Map<String,Object> paramMap = MapUtil.getParamsMap(thirdStoreInfoEntity);
		if(pb != null){
			paramMap.put("index", pb.getIndex());
			paramMap.put("length", pb.getLength());
		}
		return thirdStoreInfoMapper.queryListForPageThirdStoreInfo(paramMap);
	}
	/**
	*
	* @author henry
	* @date 2017-06-30 13:29:26
	* @see ThirdStoreInfoService#queryCountThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity)
	* @param thirdStoreInfoEntity
	* @return int
	*/
	public int queryCountThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity) {
		return thirdStoreInfoMapper.queryCountThirdStoreInfo(thirdStoreInfoEntity);
	}

	/**
	*
	* @author henry
	* @date 2017-06-30 13:29:26
	* @see ThirdStoreInfoService#queryById(Long storeId)
	* @param storeId
	* @return
	*/
	public ThirdStoreInfoEntity queryById(Long storeId) {
		return thirdStoreInfoMapper.queryById(storeId);
	}
		
	/**
	*
	* @author henry
	* @date 2017-06-30 13:29:26
	* @see ThirdStoreInfoService#queryByIds(List<String> ids)
	* @param ids
	* @return List<ThirdStoreInfoEntity>
	*/
	public List<ThirdStoreInfoEntity> queryByIds(List<String> ids) {
		return thirdStoreInfoMapper.queryByIds(ids);
	}

	/**
	*
	* @author henry
	* @date 2017-06-30 13:29:26
	* @see ThirdStoreInfoService#addThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity)
	* @param thirdStoreInfoEntity
	* @return int
	*/
	public int addThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity) {
		int i = 0;
		try{
			i = thirdStoreInfoMapper.addThirdStoreInfo(thirdStoreInfoEntity);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return i;
	}

	/**
	*
	* @author henry
	* @date 2017-06-30 13:29:26
	* @see ThirdStoreInfoService#updateThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity)
	* @param thirdStoreInfoEntity
	* @return int
	*/
	public int updateThirdStoreInfo(ThirdStoreInfoEntity thirdStoreInfoEntity) {
		int i = 0;
		try{
			i =  thirdStoreInfoMapper.updateThirdStoreInfo(thirdStoreInfoEntity);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return i;
	}
	
}

