package com.ylife.cache;

import java.io.Serializable;

/**
 * Created by InThEnd on 2017/1/12.
 * 当前的正在进行中的操作。此概念在于一个业务在开始之前会对{@link CacheObject}添加一个操作标记。
 * 直到业务结束后会移除这个标记。
 *
 * @see CacheObject#operations
 */
public class CurrentOperation implements Serializable {

    private long id;

    private long startTime;

    private Operation operation;

    public CurrentOperation(long id, Operation operation) {
        this.id = id;
        this.operation = operation;
        startTime = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    /**
     * 操作类型。
     */
    public enum Operation {
        UPDATE, DELETE, CREATE
    }

}
