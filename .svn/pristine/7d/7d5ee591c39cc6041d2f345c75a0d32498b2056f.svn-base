package com.ylife.enterprise.service;

import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;

import java.util.List;

/**
 * Created by InThEnd on 2016/8/24.
 * 企业功能服务。
 */
public interface EnterpriseFunctionService {

    /**
     * 校验支付密码
     *
     * @param funcId 企业ID
     * @param payKey 支付密码
     */
    boolean checkPayKey(long funcId, String payKey);

    /**
     * 更新支付密码
     *
     * @param funcId 企业ID
     * @param payKey 支付密码
     */
    void updatePaykey(long funcId, String payKey);

    /**
     * 获取企业功能实体。
     */
    EnterpriseFunction get(long funcId);

    /**
     * 获取下一级节点
     * @param parentId
     * @return
     */
    List<EnterpriseFunction> getByParentId(Long parentId);


    /**
     * 获取所有下级节点
     * @param enterpriseId
     * @return
     */
    List<Long> getAllNode(Long enterpriseId);
}
