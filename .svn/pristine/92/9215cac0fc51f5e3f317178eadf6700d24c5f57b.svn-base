package com.ylife.common.model;

import com.ylife.thirdplatforminterface.entity.ThirdPlatformInterfaceEntity;
import com.ylife.thirdplatforminterface.service.ThirdPlatformInterfaceService;
import com.ylife.utils.DateUtil;
import com.ylife.utils.MD5Util;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 邮政对接工具类
 * Created by Administrator on 2017/7/20.
 */
@Service
public class OpenUtil {

    //thirdPlatformInterfaceService
    private static ThirdPlatformInterfaceService thirdPlatformInterfaceService;

    @Resource(name="thirdPlatformInterfaceService")
    public  void setThirdPlatformInterfaceService(ThirdPlatformInterfaceService thirdPlatformInterfaceService) {
        OpenUtil.thirdPlatformInterfaceService = thirdPlatformInterfaceService;
    }

    /**
     * 判断是否符合规范
     * @param timestamp 时间戳
     * @param merchantId 商户编号
     * @param sign 签名验证
     * @return ApiResult
     */
    public static ApiResult isCheck(String merchantId,String timestamp,String sign){

        ApiResult apiResult=new ApiResult();
        if(StringUtil.isBlank(sign) || StringUtil.isBlank(merchantId) || StringUtil.isBlank(sign))
        {
            apiResult.setReturnCode(205);
            return  apiResult;
        }

        //判断时间戳是否符合
        if(!OpenUtil.isTimestamp(timestamp)){
            apiResult.setReturnCode(202);
            return  apiResult;
        }

        //获取授权用户信息
        ThirdPlatformInterfaceEntity ower= thirdPlatformInterfaceService.queryByMerchantId(merchantId);

        // 设置返回值
        apiResult.setMerchantId(merchantId);
        apiResult.setTimestamp(timestamp);
        apiResult.setSign(sign);
        apiResult.setThirdId(ower.getThirdId());

        String mdStr=merchantId+timestamp+ower.getMerchantKey();

        //判断token的存活时间

        Long s = DateUtil.fromString(timestamp, "yyyyMMddHHmmss").getTime() - (new Date()).getTime();

        //判断token是否符合
        if(!MD5Util.md5Hex(mdStr).equals(sign)){
            apiResult.setReturnCode(201);
            apiResult.setMessage("签名异常");
            return  apiResult;
        }else if(s>600000||s<-600000){
            apiResult.setReturnCode(209);
            apiResult.setMessage("签名过期");
            return  apiResult;
        }
        return apiResult;
    }


    /**
     *
     * @param timestamp
     * @return
     */
    public static boolean isTimestamp(String timestamp){
        if(timestamp==null){
            return false;
        }
        Long s= (new Date()).getTime() - DateUtil.fromString(timestamp,"yyyyMMddHHmmss").getTime();
        //判断传过来的时间戳是否符合
        if(Math.abs(s)<600000){
            return true;
        }
        return false;

    }
}
