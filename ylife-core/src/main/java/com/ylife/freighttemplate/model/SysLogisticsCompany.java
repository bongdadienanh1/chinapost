package com.ylife.freighttemplate.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 物流公司信息 2014-12-17
 * @author ggn
 *
 */
public class SysLogisticsCompany  implements Serializable {
    /*
     *物流公司ID
     */
    private Long logComId;
    /*
     *物流公司名称
     */
    private String name;
    /*
     *物流供公司编码
     */
    private String code;
    /*
     *快递100代码
     */
    private String kuaidi100Code;
    /*
     *网址
     */
    private String comUrl;
    /*
     *查件地址
     */
    private String queryUrl;
    /*
     *排序
     */
    private Long sort;
    /*
     *是否启用 char
     */
    private String usedStatus;
    /*
     *添加人
     */
    private Long insertId;
    /*
     *添加实际那
     */
    private Date insertDate;
    /*
     *修改人
     */
    private Long modifyId;
    /*
     *修改时间
     */
    private Date modifyDate;
    /*
     *是否删除
     */
    private Short deleteStatus;

    public Long getLogComId() {
        return logComId;
    }

    public void setLogComId(Long logComId) {
        this.logComId = logComId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKuaidi100Code() {
        return kuaidi100Code;
    }

    public void setKuaidi100Code(String kuaidi100Code) {
        this.kuaidi100Code = kuaidi100Code;
    }

    public String getComUrl() {
        return comUrl;
    }

    public void setComUrl(String comUrl) {
        this.comUrl = comUrl;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(String usedStatus) {
        this.usedStatus = usedStatus;
    }

    public Long getInsertId() {
        return insertId;
    }

    public void setInsertId(Long insertId) {
        this.insertId = insertId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Short getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Short deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
