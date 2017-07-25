package com.ylife.enterprise.service;

import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/8.
 * 企业服务
 */
public interface EnterpriseService {


    /**
     * 添加子企业
     *
     * @param parentId       父企业ID
     * @param discountPct    网折比例
     * @param end            是否为终端网点
     * @param hasPermit      是否有代客下单权限
     * @param enterpriseName 企业名称
     * @param organizationName 机构名称
     */
    long addEnterprise(long parentId, BigDecimal discountPct, boolean end, boolean hasPermit, String enterpriseName, String username, String password,String organizationName);


    /**
     * 根据ID更新企业
     *
     * @param enterpriseId 企业ID
     * @param discountPct  网点折扣比例
     * @param end          是否为终端网点
     * @param hasPermit    是否有代客下单权限
     * @param name         企业名称
     * @param payKey       支付密码
     * @param organizationName 机构名称
     */
    void editEnterprise(long enterpriseId, BigDecimal discountPct, Boolean end, Boolean hasPermit, String name, String payKey,String organizationName);

    /**
     * 根据ID获取Enterprise.
     *
     * @param enterpriseId 企业ID
     */
    Enterprise getEnterprise(long enterpriseId);

    /**
     * 根据ID获取EnterpriseFunction.
     *
     * @param enterpriseId 企业ID
     */
    EnterpriseFunction getEnterpriseFunction(long enterpriseId);

    /**
     * 根据父ID获取Enterprise列表。
     *
     * @param parentId 父企业ID
     */
    List<Enterprise> getEnterprises(long parentId);

    /**
     * 获取顶级企业。
     */
    Enterprise getTopEnterprise();

    /**
     * 校验支付密码。
     *
     * @param enterpriseId 企业ID
     * @param paykey       支付密码
     */
    boolean checkPaykey(long enterpriseId, String paykey);


    /**
     * 获取某个企业的总财富。
     *
     * @param enterpriseId 企业ID
     */
    BigDecimal getTotalWealth(long enterpriseId);


    /**
     * 获取某个企业的未分配总财富。
     *
     * @param enterpriseId 企业ID
     */
    BigDecimal getTotalNotAllocatWealth(long enterpriseId);

    /**
     * 获取某个企业持有的财富。
     */
    BigDecimal getHoldWealth(long enterpriseId);

    /**
     * 判断某个企业是否为最顶级企业（root）
     *
     * @param enterpriseId 企业ID
     */
    boolean isTop(long enterpriseId);

    /**
     * U宝转账。
     *
     * @param outId  转出企业ID
     * @param inId   转入企业ID
     * @param amount 转账数量
     */
    void transferUcoin(long outId, long inId, BigDecimal amount);

    /**
     * 修改已分配U宝值。
     *
     * @param enterpriseId 企业ID
     * @param addAmount    增加的U宝，可以为负值
     */
    void addUcoinAmount(long enterpriseId, BigDecimal addAmount);


    /**
     * 修改已分配邮宝，可以透支
     * @param enterpriseId 企业ID
     * @param amount 增加的邮宝，可以为负
     */
    void changeEnterpriseUcoin(long enterpriseId,BigDecimal amount);

    /**
     * 修改未分配U宝值，对账户正负做验证
     *
     * @param enterpriseId 企业ID
     * @param addAmount    增加的U宝，可以为负值
     */
    void addUndistributedUcoinAmount(long enterpriseId, BigDecimal addAmount);

    /**
     * 修改未分配u宝值，对账户正负不做验证
     * @param enterpriseId
     * @param addAmount
     */
    void addUndistributedUcoinAmountUchecked(long enterpriseId, BigDecimal addAmount);


    /**
     * 设置库存预警值。
     *
     * @param enterpriseId 企业ID
     * @param warning      预警值
     */
    void setInventoryForewarn(long enterpriseId, int warning);

    /**
     * 设置是否有权限。
     *
     * @param enterpriseId 企业ID
     * @param hasPermit    是否拥有权限
     */
    void setHasPermit(long enterpriseId, boolean hasPermit);

    /**
     * 删除企业。
     *
     * @param enterpriseId 企业ID
     */
    void deleteEnterprise(long enterpriseId);





}
