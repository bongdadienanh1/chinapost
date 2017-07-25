package com.ylife.common.model;

/**
 * 邮政对接返回对象
 * Created by Administrator on 2017/7/20.
 */
public class ApiResult {

    //商户编号
    private String merchantId;

    //时间戳
    private String timestamp;

    //签名（merchantId +timestamp+key通过Md5进行加密）
    private String sign;

    //返回码
    /**
     * 代码提示
     * 200:成功
     * 201:签名异常
     * 202:时间戳错误
     * 203:账户无法获取此数据
     * 204:账户被冻结
     * 205:参数错误
     * 206:用户不存在
     * 207:重复提交，已经存在
     * 208:未能获取到商城ID
     * 209:签名过期
     * 999:其他错误
     */
    private Integer returnCode = 200;

    //返回码描述信息
    private String message;

    //返回对象
    private Object data;

    /**
     * 平台编号
     */
    private Long thirdId;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public Integer getReturnCode() {
        return returnCode;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }
}
