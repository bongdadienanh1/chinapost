package com.ylife.enterprise.mapper;


import com.ylife.enterprise.model.EnterpriseFunction;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface EnterpriseFunctionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseFunction record);

    int insertSelective(EnterpriseFunction record);

    EnterpriseFunction selectByPrimaryKey(Long id);

    EnterpriseFunction selectByPrimaryKeyForUpdate(Long id);

    boolean checkPayKey(@Param("id") Long id, @Param("payKey") String payKey);

    int updateByPrimaryKeySelective(EnterpriseFunction record);

    int updateByPrimaryKey(EnterpriseFunction record);

    Long selectMaxCatalogByParentId(Long parentId);

    List<EnterpriseFunction> selectByParentIdForUpdate(Long parentId);

    //获取下一级节点
    List<EnterpriseFunction> selectByParentId(Long parentId);

    //获取某一个节点以下所有节点，包括该节点自身
    List<Long> selectByCatalog(@Param("maxCatalog")Long maxCatalog,@Param("minCatalog")Long minCatalog);

    Set<Long> selectCatalogsByParentId(Long parentId);

    int updateUndistributedPrice(@Param("id") Long id, @Param("backSubsidyPrice") BigDecimal backSubsidyPrice);
}