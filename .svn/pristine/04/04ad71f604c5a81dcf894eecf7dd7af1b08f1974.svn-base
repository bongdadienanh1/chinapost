package com.ylife.authority.mapper;

import com.ylife.authority.model.AuthorityPageKey;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/10.
 */
public interface AuthorityResoucePageMapper {

    void deleteByPrimaryKey(AuthorityPageKey key);

    void insert(AuthorityPageKey key);

    void batchInsert(List<AuthorityPageKey> keys);

    boolean exist(AuthorityPageKey key);

    void deleteByAuthorityId(Long authorityId);

    List<Long> selectAuthorityIdByPageId(Long pageId);

    List<Long> selectAuthorityIdAndParentByPageId(Long pageId);
}
