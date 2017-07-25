package com.ylife.customer.service.impl;

import com.ylife.customer.mapper.ChinapostTagMapper;
import com.ylife.customer.model.ChinapostTag;
import com.ylife.customer.service.ChinapostTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by InThEnd on 2016/5/19.
 * ChinapostTagServiceImpl
 */
@Service
public class ChinapostTagServiceImpl implements ChinapostTagService {

    @Resource
    private ChinapostTagMapper chinapostTagMapper;

    @Override
    public List<ChinapostTag> getTags() {
        return chinapostTagMapper.selectAll();
    }

}
