package com.ylife.thirdplatforminterface.service.impl;


import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.thirdplatforminterface.entity.ThirdPlatformInterfaceEntity;
import com.ylife.thirdplatforminterface.mapper.ThirdPlatformInterfaceMapper;
import com.ylife.thirdplatforminterface.service.ThirdPlatformInterfaceService;
import com.ylife.utils.MyLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service(value="thirdPlatformInterfaceService")
public class ThirdPlatformInterfaceServiceImpl implements ThirdPlatformInterfaceService {
	/**
	 * 日志
	 * */
	public static final MyLogger LOGGER = new MyLogger(ThirdPlatformInterfaceServiceImpl.class);

	private Generator generator = IdGeneratorFactory.create("MERCHANT");

	@Resource
	private ThirdPlatformInterfaceMapper thirdPlatformInterfaceMapper;

	/**
	*
	* @author henry
	* @date 2017-07-19 10:41:20
	* @see ThirdPlatformInterfaceService#queryById(Long id)
	* @param id
	* @return
	*/
	public ThirdPlatformInterfaceEntity queryById(Long id) {
		return thirdPlatformInterfaceMapper.queryById(id);
	}

	/**
	*
	* @author henry
	* @date 2017-07-19 10:41:20
	* @see ThirdPlatformInterfaceService#queryById(Long id)
	* @param thirdId
	* @return
	*/
	public ThirdPlatformInterfaceEntity queryByThirdId(Long thirdId) {
		return thirdPlatformInterfaceMapper.queryByThirdId(thirdId);
	}

	@Override
	public ThirdPlatformInterfaceEntity queryByMerchantId(String merchantId) {
		return thirdPlatformInterfaceMapper.queryByMerchantId(merchantId);
	}

	/**
	*
	* @author henry
	* @date 2017-07-19 10:41:20
	* @see ThirdPlatformInterfaceService#addThirdPlatformInterface(ThirdPlatformInterfaceEntity thirdPlatformInterfaceEntity)
	* @param thirdPlatformInterfaceEntity
	* @return int
	*/
	public int addThirdPlatformInterface(ThirdPlatformInterfaceEntity thirdPlatformInterfaceEntity) {
		thirdPlatformInterfaceEntity.setMerchantId(String.valueOf(generator.generate()));
		thirdPlatformInterfaceEntity.setMerchantKey(getMerchantKey());
		int i = 0;
		try{
			i = thirdPlatformInterfaceMapper.addThirdPlatformInterface(thirdPlatformInterfaceEntity);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return i;
	}

	/**
	*
	* @author henry
	* @date 2017-07-19 10:41:20
	* @see ThirdPlatformInterfaceService#updateThirdPlatformInterface(ThirdPlatformInterfaceEntity thirdPlatformInterfaceEntity)
	* @param thirdPlatformInterfaceEntity
	* @return int
	*/
	public int updateThirdPlatformInterface(ThirdPlatformInterfaceEntity thirdPlatformInterfaceEntity) {
		int i = 0;
		try{
			i =  thirdPlatformInterfaceMapper.updateThirdPlatformInterface(thirdPlatformInterfaceEntity);;
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return i;
	}

	/**
	 * 秘钥生成
	 * @return
	 */
	private String getMerchantKey(){
		// 自动生成key
		String base = "ABCDEFGHIJKLMNGPQRSTUVWXYZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 20; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

    /**
     * 根据店铺Id获取ThirdPlatformInterfaceEntity
     *
     * @param storeId
     * @return
     */
    @Override
    public ThirdPlatformInterfaceEntity queryByStoreId(Long storeId) {
        return thirdPlatformInterfaceMapper.queryByStoreId(storeId);
    }
}

