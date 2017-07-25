package com.ylife.main.mapper;


import com.ylife.main.model.MobSubject;

import java.util.List;

/**
 * @ClassName: MobSubjectMapper
 * @Description: DAO-移动版专题
 * @author Henry
 * @date 2014年10月11日 下午1:51:49
 */
public interface MobSubjectMapper {
    /**
     * @Title: deleteByPrimaryKey
     * @Description: 根据ID删除
     * @param subjectId
     * @return
     */
    int deleteByPrimaryKey(Long subjectId);

    /**
     * @Title: insert
     * @Description: 添加
     * @param record
     * @return
     */
    int insert(MobSubject record);

    /**
     * @Title: insertSelective
     * @Description: 添加-字段可选
     * @param record
     * @return
     */
    int insertSelective(MobSubject record);

    /**
     * @Title: updateByPrimaryKeySelective
     * @Description: 修改-字段可选
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobSubject record);

    /**
     * @Title: updateByPrimaryKeyWithBLOBs
     * @Description: 修改-包括xml字符串
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(MobSubject record);

    /**
     * @Title: updateByPrimaryKey
     * @Description: 修改-包括xml字符串
     * @param record
     * @return
     */
    int updateByPrimaryKey(MobSubject record);

    /**
     * @Title: selectByMerchantId
     * @Description: 根据商家ID查询
     * @param merchantId
     * @return
     */
    MobSubject selectByMerchantId(Long merchantId);

    /**
     * @Title: selectByPrimaryKey
     * @Description: 根据ID获取模板信息
     * @param subjectId
     * @return
     */
    MobSubject selectByPrimaryKey(Long subjectId);

    /**
     * @Title:selectAllSubjectUnstatusByMerchantId
     * @Description:根据商家ID查询该商家的所有专题
     * @param merchantId
     * @return
     */
    List<MobSubject> selectAllSubjectUnstatusByMerchantId(Long merchantId);

    /**
     * @Title:updateByMerchantId
     * @Description:根据商家ID修改模板的启用状态,都改成不启用
     * @param merchantId
     * @return
     */
    int updateByMerchantId(Long merchantId);

    /**
     * @Title:updateByhomepageIdAndMerchantId
     * @Description:根据商家ID和模板ID找到模板，修改启用状态为启用
     * @param record
     * @return
     */
    int updateByhomepageIdAndMerchantId(MobSubject record);

    /**
     * @Title:queryCurrSubjectByMerchantId
     * @Description:根据商家ID获取该商家当前使用的专题信息
     * @param merchantId
     * @return
     */
    MobSubject queryCurrSubjectBySubjectId(Long merchantId);

    /**
     * @Title:updateSubjectById
     * @Description:根据商家ID修改该商家当前使用的专题信息
     * @param subject
     * @return
     */
    void updateSubjectById(Long subject, String title);
}
