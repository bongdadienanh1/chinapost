package com.ylife.system.model;

import java.util.Date;

public class BusinessTypeHistory {

    private Long historyId;

    private Long typeId;

    private String operation;

    private String note;

    private String beforeEx;

    private String afterEx;

    private String operator;

    private Date date;

    private String typeName;

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getBeforeEx() {
        return beforeEx;
    }

    public void setBeforeEx(String beforeEx) {
        this.beforeEx = beforeEx == null ? null : beforeEx.trim();
    }

    public String getAfterEx() {
        return afterEx;
    }

    public void setAfterEx(String afterEx) {
        this.afterEx = afterEx == null ? null : afterEx.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}