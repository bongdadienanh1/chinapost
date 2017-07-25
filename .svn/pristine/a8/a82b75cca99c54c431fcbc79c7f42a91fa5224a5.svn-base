package com.ylife.enterprise.service.impl;

import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.service.EnterpriseFunctionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by InThEnd on 2016/8/24.
 * EnterpriseFunctionServiceImpl
 */
@Service
public class EnterpriseFunctionServiceImpl implements EnterpriseFunctionService {

    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;


    @Override
    public boolean checkPayKey(long funcId, String payKey) {
        return enterpriseFunctionMapper.checkPayKey(funcId, payKey);
    }

    @Override
    public void updatePaykey(long funcId, String payKey) {
        EnterpriseFunction model = new EnterpriseFunction();
        model.setId(funcId);
        model.setPaykey(payKey);
        enterpriseFunctionMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public EnterpriseFunction get(long funcId) {
        return enterpriseFunctionMapper.selectByPrimaryKey(funcId);
    }

    @Override
    public List<EnterpriseFunction> getByParentId(Long parentId) {
        return enterpriseFunctionMapper.selectByParentId(parentId);
    }

    @Override
    public List<Long> getAllNode(Long enterpriseId) {
        EnterpriseFunction function=enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog=function.getMaxCatalog();
        Long minCatalog=function.getMinCatalog();
        return enterpriseFunctionMapper.selectByCatalog(maxCatalog,minCatalog);
    }
}
