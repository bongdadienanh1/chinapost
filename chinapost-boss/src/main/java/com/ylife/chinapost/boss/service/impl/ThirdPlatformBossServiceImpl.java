package com.ylife.chinapost.boss.service.impl;


import com.ylife.chinapost.boss.service.ThirdPlatformBossService;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.thirdstoreinfo.entity.ThirdStoreInfoEntity;
import com.ylife.thirdstoreinfo.service.ThirdStoreInfoService;
import com.ylife.utils.ExcelUtil;
import com.ylife.utils.MapUtil;
import com.ylife.utils.MyLogger;
import com.ylife.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.ylife.thirdplatform.service.ThirdPlatformService;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import org.springframework.transaction.annotation.Transactional;

@Service(value="thirdPlatformBossService")
public class ThirdPlatformBossServiceImpl implements ThirdPlatformBossService {
	/**
	* 日志
	* */
	public static final MyLogger LOGGER = new MyLogger(ThirdPlatformBossServiceImpl.class);

	@Resource(name="thirdPlatformService")
	private ThirdPlatformService thirdPlatformService;
	@Resource(name="thirdStoreInfoService")
	private ThirdStoreInfoService thirdStoreInfoService;
	@Resource
	private GoodsInfoService goodsInfoService;

	/**
	*
	* @author  henry
	* @date 2017-06-30 09:14:43
	* @see ThirdPlatformBossService#addThirdPlatform(ThirdPlatformEntity thirdPlatformEntity)
	* @param thirdPlatformEntity
	* @return int
	*/
	@Transactional
	public boolean addThirdPlatform(ThirdPlatformEntity thirdPlatformEntity) {
		boolean flag = thirdPlatformService.addThirdPlatform(thirdPlatformEntity) > 0?true:false;
		if(flag){
			thirdStoreInfoService.addThirdStoreInfo(getThirdStoreInfo(thirdPlatformEntity));
		}
		return flag;
	}

	/**
	*
	* @author henry
	* @date 2017-06-30 09:14:43
	* @see ThirdPlatformBossService#updateThirdPlatform(ThirdPlatformEntity thirdPlatformEntity)
	* @param thirdPlatformEntity
	* @return ResponseEntity
	*/
	@Transactional
	public boolean updateThirdPlatform(ThirdPlatformEntity thirdPlatformEntity) {
		boolean deleteFlag = true;
		Map<String,Object> paramMap = MapUtil.getParamsMap(thirdPlatformEntity);
		if(thirdPlatformEntity.getIds()!=null && thirdPlatformEntity.getIds().length > 0){
			paramMap.put("ids", Arrays.asList(thirdPlatformEntity.getIds()));
			paramMap.put("deleteFlag","1");
			deleteFlag = false;
			// 查询storeId
			List<ThirdPlatformEntity> list = thirdPlatformService.queryByIds(Arrays.asList(thirdPlatformEntity.getIds()));
			String []arry = new String[list.size()];
			for (int i=0;i<list.size();i++){
				arry[i] = list.get(i).getId().toString();
			}
			// 删除货品
			goodsInfoService.deleteGoodsInfo(thirdPlatformEntity.getIds());
		}else if("1".equals(thirdPlatformEntity.getDeleteFlag())){
			// 查询storeId
			ThirdPlatformEntity platformEntity = thirdPlatformService.queryById(thirdPlatformEntity.getId());
			// 删除货品
			goodsInfoService.deleteGoodsInfo(new String[]{String.valueOf(platformEntity.getStoreId())});
		}

		boolean flag = thirdPlatformService.updateThirdPlatform(paramMap) > 0?true:false;
		if(flag && deleteFlag){
			thirdStoreInfoService.updateThirdStoreInfo(getThirdStoreInfo(thirdPlatformEntity));
		}
		return flag;
	}

	/**
	 * 检查第三方生成店家是否已存在
	 * @author henry
	 * @date 2017-06-30 09:14:43
	 * @see ThirdPlatformBossService#checkThirdUser(ThirdPlatformEntity thirdPlatformEntity)
	 * @param thirdPlatformEntity
	 * @return
	 */
	public String checkThirdUser(ThirdPlatformEntity thirdPlatformEntity){
		String result = "200";
		ThirdPlatformEntity platformEntity;
		if(!StringUtil.isBlank(thirdPlatformEntity.getCompanyName())){
			platformEntity = new ThirdPlatformEntity();
			platformEntity.setCompanyName(thirdPlatformEntity.getCompanyName());
			platformEntity.setDeleteFlag("0");
			if(thirdPlatformService.queryCountThirdPlatform(platformEntity) > 0){
				result = "公司已存在！！！";
				return result;
			}
		}

		if(!StringUtil.isBlank(thirdPlatformEntity.getStoreName())){
			platformEntity = new ThirdPlatformEntity();
			platformEntity.setStoreName(thirdPlatformEntity.getStoreName());
            platformEntity.setDeleteFlag("0");
			if(thirdPlatformService.queryCountThirdPlatform(platformEntity) > 0){
				result = "店铺名称已被使用！！！";
				return result;
			}
		}

		if(!StringUtil.isBlank(thirdPlatformEntity.getThirdUserName())){
			platformEntity = new ThirdPlatformEntity();
			platformEntity.setThirdUserName(thirdPlatformEntity.getThirdUserName());
            platformEntity.setDeleteFlag("0");
			if(thirdPlatformService.queryCountThirdPlatform(platformEntity) > 0){
				result = "账号已被使用！！！";
				return result;
			}
		}
		return result;
	}

	/**
	*
	* @author  henry
	* @date 2017-06-30 09:14:43
	* @see ThirdPlatformBossService#queryAllThirdPlatform(ThirdPlatformEntity thirdPlatformEntity)
	* @param thirdPlatformEntity
	* @return List
	*/
	public List<ThirdPlatformEntity> queryAllThirdPlatform(ThirdPlatformEntity thirdPlatformEntity) {
		thirdPlatformEntity.setDeleteFlag("0");
		thirdPlatformEntity.setCheck("0");
		return thirdPlatformService.queryAllThirdPlatform(thirdPlatformEntity);
	}
	/**
	*
	* @author  henry
	* @date 2017-06-30 09:14:43
	* @see ThirdPlatformBossService#queryListForPageThirdPlatform(ThirdPlatformEntity thirdPlatformEntity,Pageable pb)
	* @param thirdPlatformEntity
	* @param pb
	* @return Page
	*/
	public Page<ThirdPlatformEntity> queryListForPageThirdPlatform(ThirdPlatformEntity thirdPlatformEntity, Pageable pb) {
		thirdPlatformEntity.setDeleteFlag("0");
		thirdPlatformEntity.setCheck("0");
		List<ThirdPlatformEntity> list = thirdPlatformService.queryListForPageThirdPlatform(thirdPlatformEntity,pb);
		int totalElements = thirdPlatformService.queryCountThirdPlatform(thirdPlatformEntity);
		return new PageImpl<ThirdPlatformEntity>(pb, totalElements, list);
	}
	/**
	*
	* @author  henry
	* @date 2017-06-30 09:14:43
	* @see ThirdPlatformBossService#queryById(Long id)
	* @param id
	* @return
	*/
	public ThirdPlatformEntity queryById(Long id) {
		return thirdPlatformService.queryById(id);
	}

    /**
    *
    * @author  henry
    * @date 2017-06-30 09:14:43
    * @see ThirdPlatformBossService#exportThirdPlatform(ThirdPlatformEntity thirdPlatformEntity,String ids, HttpServletResponse response)
    * @param thirdPlatformEntity
    * @param ids
    * @param response
    */
	public void exportThirdPlatform(ThirdPlatformEntity thirdPlatformEntity,String ids, HttpServletResponse response) {
		List<ThirdPlatformEntity> exportList = null;
        List<Object> list = new ArrayList<Object>();
		if (StringUtils.isEmpty(ids)) {
			exportList =thirdPlatformService.queryAllThirdPlatform(thirdPlatformEntity);
		} else {
			String[] idArray = ids.split(",");
			List<String> idList = new ArrayList<String>();
			for(String id : idArray) {
				idList.add(id);
			}
			exportList = thirdPlatformService.queryByIds(idList);
		}
		list.addAll(exportList);
        String[] titleTag = {"平台登录账号","密码","平台类型，0为C店，1为抛单","删除标识（0未删除，1删除）","创建时间","修改时间"};
        String[] fieldsArr = {"thirdUserName","thirdUserPwd","platformType","deleteFlag","createTime","modifyTime"};
        try{
        	ExcelUtil.createReport(list,"ThirdPlatformEntity信息列表",titleTag,fieldsArr,"ThirdPlatformEntity信息列表",response);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
	}

	/**
	 * 获取对象
	 * @param thirdPlatformEntity
	 * @return
	 */
	private static ThirdStoreInfoEntity getThirdStoreInfo(ThirdPlatformEntity thirdPlatformEntity){
		ThirdStoreInfoEntity thirdStoreInfoEntity = new ThirdStoreInfoEntity();
		thirdStoreInfoEntity.setCompanyName(thirdPlatformEntity.getCompanyName());
		thirdStoreInfoEntity.setStoreName(thirdPlatformEntity.getStoreName());
		thirdStoreInfoEntity.setThirdPlatformId(thirdPlatformEntity.getId());
		return thirdStoreInfoEntity;
	}
}
