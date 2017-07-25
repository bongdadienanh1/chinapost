package com.ylife.enterprise.service.impl;

import com.ylife.authority.model.Authority;
import com.ylife.authority.service.AuthorityService;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.mapper.EnterpriseInfoMapper;
import com.ylife.enterprise.mapper.EnterpriseManagerMapper;
import com.ylife.enterprise.mapper.EnterpriseMapper;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseManagerService;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.UserOperationException;
import com.ylife.utils.Assert;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Created by InThEnd on 2016/4/8.
 * EnterpriseServiceImpl
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private EnterpriseInfoMapper enterpriseInfoMapper;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private EnterpriseManagerMapper enterpriseManagerMapper;
    @Resource
    private EnterpriseManagerService enterpriseManagerService;
    @Resource
    private AuthorityService authorityService;

    @Override
    @Transactional
    public long addEnterprise(long parentId, BigDecimal discountPct, boolean end, boolean hasPermit, String enterpriseName, String username, String password,String organizationName) {
        EnterpriseFunction function = new EnterpriseFunction();
        function.setParentId(parentId);
        function.setDiscountPct(discountPct);
        function.setCatalog(generateCatalog(parentId));
        function.setEnd(end);
        function.setHasPermit(hasPermit);
        enterpriseFunctionMapper.insertSelective(function);
        Long enterpriseId = function.getId();
        EnterpriseInfo info = new EnterpriseInfo();
        info.setEnterpriseId(enterpriseId);
        info.setEnterpriseName(enterpriseName);

        //机构名称
        info.setAccountName(organizationName);

        //添加企业信息
        try {
            enterpriseInfoMapper.insertSelective(info);
        }catch (DuplicateKeyException e) {
            throw new UserOperationException("机构编号不能重复！");
        }

        //添加账号
        long managerId = enterpriseManagerService.addEnterpriseManager(enterpriseId, true, username, password, null, null, null, null);

        //添加账号对应的权限，145默认下级账号权限ID
        if(end){//网点权限
            Authority secondAdmin = authorityService.getSecondAdmin();
            Assert.notNull(secondAdmin, "网点权限缺失，请联系管理员。");
            authorityService.setManagerAuthority(managerId, secondAdmin.getId());
        }else{//中间级权限
            Authority notAdmin = authorityService.getNotAdmin();
            Assert.notNull(notAdmin, "中间级管理员权限缺失，请联系管理员。");
            authorityService.setManagerAuthority(managerId, notAdmin.getId());
        }

        return enterpriseId;
    }

    /**
     * 生成数字索引
     */
    private long generateCatalog(long parentId) {
        EnterpriseFunction parent = enterpriseFunctionMapper.selectByPrimaryKey(parentId);
        long fatherCatalog = parent.getCatalog();
        long fatherTail = EnterpriseFunction.tail(fatherCatalog);
        long sonTail = fatherTail / 100;
        if (sonTail == 0) {
            throw new IllegalArgumentException("尾巴长度不够了！");
        }
        Set<Long> catalogs = enterpriseFunctionMapper.selectCatalogsByParentId(parentId);
        int sonSerial = 1;
        while (sonSerial < 100) {
            long catalog = fatherCatalog + sonSerial * sonTail;
            if (catalogs.contains(catalog)) {
                sonSerial++;
            } else {
                return catalog;
            }
        }
        throw new IllegalArgumentException("儿子太多了！最多99个儿子！");
    }

    @Override
    public void editEnterprise(long enterpriseId, BigDecimal discountPct, Boolean end, Boolean hasPermit, String name, String payKey,String organizationName) {
        if (end != null && end) {
            List<Enterprise> enterprises = getEnterprises(enterpriseId);
            if (enterprises.size() != 0) {
                throw new UserOperationException("此节点存在下级账号，无法设置为终端网点。");
            }
        }

        EnterpriseFunction function = new EnterpriseFunction();
        function.setId(enterpriseId);
        function.setPaykey(payKey);
        function.setDiscountPct(discountPct);
        function.setEnd(end);
        function.setHasPermit(hasPermit);
        enterpriseFunctionMapper.updateByPrimaryKeySelective(function);
        if (name != null) {
            EnterpriseInfo info = new EnterpriseInfo();
            info.setEnterpriseId(enterpriseId);
            info.setEnterpriseName(name);
            info.setAccountName(organizationName);
            try {
                enterpriseInfoMapper.updateByPrimaryKeySelective(info);
            }catch (DuplicateKeyException e) {
                throw new UserOperationException("机构编号不能重复！");
            }
        }
    }

    @Override
    public Enterprise getEnterprise(long enterpriseId) {
        return enterpriseMapper.selectByPrimaryKey(enterpriseId);
    }

    @Override
    public EnterpriseFunction getEnterpriseFunction(long enterpriseId) {
        return enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
    }

    @Override
    public List<Enterprise> getEnterprises(long parentId) {
        return enterpriseMapper.selectByParentId(parentId);
    }

    @Override
    public Enterprise getTopEnterprise() {
        return enterpriseMapper.selectByParentIdIsNull();
    }

    @Override
    public boolean checkPaykey(long enterpriseId, String paykey) {
        return enterpriseMapper.checkPaykey(enterpriseId, paykey);
    }

    @Override
    public BigDecimal getTotalWealth(long enterpriseId) {
        BigDecimal total = new BigDecimal("0.00");
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        total = total.add(enterprise.getUndistributedPrice());
        total = total.add(enterprise.getUcoinPrice());
        List<Enterprise> sons = enterpriseMapper.selectByParentId(enterpriseId);
        for (Enterprise son : sons) {
            BigDecimal decimal = getTotalWealth(son.getId());
            total = total.add(decimal);
        }
        return total;
    }

    @Override
    public BigDecimal getTotalNotAllocatWealth(long enterpriseId) {
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        return enterprise.getUndistributedPrice();
    }

    @Override
    public BigDecimal getHoldWealth(long enterpriseId) {
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        return enterprise.getUcoinPrice();
    }

    @Override
    public boolean isTop(long enterpriseId) {
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        return enterprise.getParentId() == null;
    }

    @Override
    @Transactional
    public void transferUcoin(long outId, long inId, BigDecimal amount) {
        addUndistributedUcoinAmount(outId, amount.negate());
        addUndistributedUcoinAmountUchecked(inId, amount);
    }

    @Override
    @Transactional
    public void addUcoinAmount(long enterpriseId, BigDecimal addAmount) {
        enterpriseMapper.updateUcoinPriceByPrimaryKey(enterpriseId, addAmount);
        BigDecimal ucoin = enterpriseMapper.selectUcoinPriceByPrimaryKey(enterpriseId);
        if (ucoin.compareTo(BigDecimal.ZERO) == -1) {
            throw new UserOperationException("网点余额不足。");
        }
    }

    @Override
    @Transactional
    public void changeEnterpriseUcoin(long enterpriseId, BigDecimal amount) {
        enterpriseMapper.updateUcoinPriceByPrimaryKey(enterpriseId,amount);
    }

    @Override
    @Transactional
    public void addUndistributedUcoinAmount(long enterpriseId, BigDecimal addAmount) {
        enterpriseMapper.updateUndistributedPriceByPrimaryKey(enterpriseId, addAmount);
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        if (function.getUndistributedPrice().compareTo(BigDecimal.ZERO) == -1) {
            throw new UserOperationException("余额不足。");
        }
    }

    @Override
    @Transactional
    public void addUndistributedUcoinAmountUchecked(long enterpriseId, BigDecimal addAmount) {
        enterpriseMapper.updateUndistributedPriceByPrimaryKey(enterpriseId, addAmount);
    }

    @Override
    public void setInventoryForewarn(long enterpriseId, int warning) {
        Assert.isTrue(warning >= 0, "库存预警值必须大于等于0");
        EnterpriseFunction function = new EnterpriseFunction();
        function.setId(enterpriseId);
        function.setInventoryForewarn(warning);
        enterpriseFunctionMapper.updateByPrimaryKeySelective(function);
    }

    @Override
    public void setHasPermit(long enterpriseId, boolean hasPermit) {
        EnterpriseFunction function = new EnterpriseFunction();
        function.setId(enterpriseId);
        function.setHasPermit(hasPermit);
        enterpriseFunctionMapper.updateByPrimaryKeySelective(function);
    }

    @Override
    @Transactional
    public void deleteEnterprise(long enterpriseId) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        if (function.getUndistributedPrice().compareTo(new BigDecimal(0)) == 1 || function.getUcoinPrice().compareTo(new BigDecimal(0)) == 1) {
            throw new UserOperationException("当前账户存在余额，不能删除。");
        }
        List<EnterpriseFunction> functions = enterpriseFunctionMapper.selectByParentIdForUpdate(enterpriseId);
        if (functions != null && functions.size() > 0) {
            throw new UserOperationException("当前账户存在下级，不能删除。");
        }
        enterpriseFunctionMapper.deleteByPrimaryKey(enterpriseId);
        enterpriseInfoMapper.deleteByPrimaryKey(enterpriseId);
        enterpriseManagerMapper.deleteByEnterpriseId(enterpriseId);
    }


}
