package com.ylife.manager.mapper;

import com.ylife.manager.model.Manager;

import java.util.Map;

/**
 * Created by suibian on 2017-06-12.
 */
public interface ManagerMapper {
    /**
     * 查找管理员
     *
     * @param paramMap
     *            参数列表
     * @return 管理员 {@link Manager}
     */
    Manager selectCustomerByNamePwd(Map<String, Object> paramMap);
}
