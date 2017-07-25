package com.ylife.authority.service.impl;

import com.ylife.authority.mapper.ResourcePageMapper;
import com.ylife.authority.model.ResourcePage;
import com.ylife.authority.service.ResourcePageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/9.
 * <p/>
 * ResourcePageServiceImpl
 */
@Service
public class ResourcePageServiceImpl implements ResourcePageService {

    @Resource
    ResourcePageMapper resourcePageMapper;

    @Override
    public List<ResourcePage> getTopPages() {
        return resourcePageMapper.selectByParentIdisNull();
    }

    @Override
    public List<ResourcePage> getSonPages(long pageId) {
        return resourcePageMapper.selectByParentId(pageId);
    }

    @Override
    public List<ResourcePage> getAuthorityPages(long authorityId) {
        return resourcePageMapper.selectByAuthorityId(authorityId);
    }
}
