package com.ylife.thirdplatform.service.impl;


import com.ylife.data.page.Pageable;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import com.ylife.thirdplatform.mapper.ThirdPlatformMapper;
import com.ylife.thirdplatform.service.ThirdPlatformService;
import com.ylife.thirdstoreinfo.entity.ThirdStoreInfoEntity;
import com.ylife.thirdstoreinfo.mapper.ThirdStoreInfoMapper;
import com.ylife.utils.DateUtil;
import com.ylife.utils.MapUtil;
import com.ylife.utils.MyLogger;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value="thirdPlatformService")
public class ThirdPlatformServiceImpl implements ThirdPlatformService {
	/**
	 * 日志
	 * */
	public static final MyLogger LOGGER = new MyLogger(ThirdPlatformServiceImpl.class);

	@Resource
	private ThirdPlatformMapper thirdPlatformMapper;

	/**
	 *
	 * @author henry
	 * @date 2017-06-30 08:52:14
	 * @see ThirdPlatformService#queryAllThirdPlatform(ThirdPlatformEntity thirdPlatformEntity)
	 * @param thirdPlatformEntity
	 * @return List<ThirdPlatformEntity>
	 */
	public List<ThirdPlatformEntity> queryAllThirdPlatform(ThirdPlatformEntity thirdPlatformEntity) {
		return thirdPlatformMapper.queryAllThirdPlatform(thirdPlatformEntity);
	}
	/**
	 *
	 * @author henry
	 * @date 2017-06-30 08:52:14
	 * @see ThirdPlatformService#queryAllThirdPlatform(ThirdPlatformEntity thirdPlatformEntity)
	 * @param thirdPlatformEntity
	 * @return List<ThirdPlatformEntity>
	 */
	public List<ThirdPlatformEntity> queryListForPageThirdPlatform(ThirdPlatformEntity thirdPlatformEntity, Pageable pb) {
		Map<String,Object> paramMap = MapUtil.getParamsMap(thirdPlatformEntity);
		if(pb != null){
			paramMap.put("index", pb.getIndex());
			paramMap.put("length", pb.getLength());
		}
		return thirdPlatformMapper.queryListForPageThirdPlatform(paramMap);
	}
	/**
	 *
	 * @author henry
	 * @date 2017-06-30 08:52:14
	 * @see ThirdPlatformService#queryCountThirdPlatform(ThirdPlatformEntity thirdPlatformEntity)
	 * @param thirdPlatformEntity
	 * @return int
	 */
	public int queryCountThirdPlatform(ThirdPlatformEntity thirdPlatformEntity) {
		return thirdPlatformMapper.queryCountThirdPlatform(thirdPlatformEntity);
	}

	/**
	 *
	 * @author henry
	 * @date 2017-06-30 08:52:14
	 * @see ThirdPlatformService#queryById(Long id)
	 * @param id
	 * @return
	 */
	public ThirdPlatformEntity queryById(Long id) {
		return thirdPlatformMapper.queryById(id);
	}

	/**
	 *
	 * @author henry
	 * @date 2017-06-30 08:52:14
	 * @see ThirdPlatformService#queryByIds(List<String> ids)
	 * @param ids
	 * @return List<ThirdPlatformEntity>
	 */
	public List<ThirdPlatformEntity> queryByIds(List<String> ids) {
		return thirdPlatformMapper.queryByIds(ids);
	}

	/**
	 *
	 * @author henry
	 * @date 2017-06-30 08:52:14
	 * @see ThirdPlatformService#addThirdPlatform(ThirdPlatformEntity thirdPlatformEntity)
	 * @param thirdPlatformEntity
	 * @return int
	 */
	public int addThirdPlatform(ThirdPlatformEntity thirdPlatformEntity) {
		int i = 0;
		try{
			if(StringUtil.isBlank(thirdPlatformEntity.getPlatformType())){
				thirdPlatformEntity.setPlatformType("0");
			}
			thirdPlatformEntity.setCreateTime(DateUtil.formatToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
			i = thirdPlatformMapper.addThirdPlatform(thirdPlatformEntity);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return i;
	}

	/**
	 *
	 * @author henry
	 * @date 2017-06-30 08:52:14
	 * @see ThirdPlatformService#updateThirdPlatform(Map<String,Object> paramMap)
	 * @param paramMap
	 * @return int
	 */
	public int updateThirdPlatform(Map<String,Object> paramMap) {
		int i = 0;
		try{
			paramMap.put("modifyTime",DateUtil.formatToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
			i =  thirdPlatformMapper.updateThirdPlatform(paramMap);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return i;
	}
	/**
	 * 根据用户名密码获取用户信息
	 * @param userName 用户名
	 * @param password 密码
	 * @return int
	 * */
	@Override
	public ThirdPlatformEntity queryByUserNameAndPwd(String userName, String password) {
		ThirdPlatformEntity thirdPlatformEntity = new ThirdPlatformEntity();
		thirdPlatformEntity.setThirdUserName(userName);
		thirdPlatformEntity.setThirdUserPwd(password);
        thirdPlatformEntity.setDeleteFlag("0");
		return thirdPlatformMapper.queryByUserNameAndPwd(thirdPlatformEntity);
	}


}

