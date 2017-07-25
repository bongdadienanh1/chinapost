/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.main.service.impl;


import com.ylife.main.mapper.MobSubjectMapper;
import com.ylife.main.model.MobSubject;
import com.ylife.main.service.MobSubjectService;
import com.ylife.utils.xml.XmlUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * SERVICE实现类-移动版可视化配置首页
 * 
 * @ClassName: MobSubjectServiceImpl
 * @Description: 移动版可视化配置专题的SERVICE实现
 * @author Henry
 * @date 2014年10月17日 上午10:02:33
 * 
 */
@Service
public class MobSubjectServiceImpl implements MobSubjectService {

    @Override
    public void createHomePage(MobSubject homePage) {
        homePage.setTemp2("0");
        homePage.setUpdateDate(new Date());
        homePage.setUpdateUserId(getLoginUserId());
        this.mobSubjectMapper.insertSelective(homePage);
    }

    @Override
    public void updateSubject(MobSubject homePage) {
        homePage.setUpdateDate(new Date());
        homePage.setUpdateUserId(getLoginUserId());
        this.mobSubjectMapper.updateByPrimaryKeySelective(homePage);
    }

    @Override
    public MobSubject selectHomePageByMerchantId(Long merchantId) {
        MobSubject homePage = mobSubjectMapper.selectByMerchantId(merchantId);
        if (null == homePage) {
            //initHomePage(merchantId);
            homePage = mobSubjectMapper.selectByMerchantId(merchantId);
        }
        return homePage;
    }

    @Override
    public MobSubject initHomePage(MobSubject homePage) {
        XmlUtil xmlUtil = new XmlUtil();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String filePath = request.getSession().getServletContext().getRealPath("/");
        String fileName = filePath + xmlFilePath;
        Document document = xmlUtil.parserXml(fileName);

        String xmlStr = xmlUtil.document2Str(document);
        homePage.setDoc(xmlStr);
        homePage.setDocBac(xmlStr);
        homePage.setUpdateDate(new Date());
        homePage.setUpdateUserId(getLoginUserId());
        createHomePage(homePage);
        return homePage;
    }

    @Override
    public void makeHtml() {
        XmlUtil xmlUtil = new XmlUtil();
        xmlUtil.Transform("xmlFileName", "xslFileName", "htmlFileName");
    }

    @Override
    public void deleteHomePageById(Long subjectId) {
        mobSubjectMapper.deleteByPrimaryKey(subjectId);

    }

    @Override
    public MobSubject getSubjectById(Long subjectId) {

        return mobSubjectMapper.selectByPrimaryKey(subjectId);
    }

    @Override
    public void openHomePageByHIdAndMId(Long homepageId, Long merchantId) {

        mobSubjectMapper.updateByMerchantId(merchantId);
        MobSubject MobSubject = new MobSubject();
        MobSubject.setSubjectId(homepageId);
        MobSubject.setMerchantId(merchantId);
        mobSubjectMapper.updateByhomepageIdAndMerchantId(MobSubject);

    }

    
    @Override
    public List<MobSubject> selectAllSubjectUnstatusByMerchantId(Long merchantId) {

        return mobSubjectMapper.selectAllSubjectUnstatusByMerchantId(merchantId);
    }

    
    @Override
    public MobSubject getCurrSubjectByMerchantId(Long merchantId) {

        return mobSubjectMapper.queryCurrSubjectBySubjectId(merchantId);
    }

    /*
     * 获取当前操作的用户ID
     */
    private Long getLoginUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (Long) request.getAttribute("loginUserId");
    }
    
    @Override
    public void updateSubjectById(Long subjectId, String title) {
    	
    }

    static final String xmlFilePath = "mobile_home_page/xml/9gdemo.xml";

    @Resource
    private MobSubjectMapper mobSubjectMapper;

}
