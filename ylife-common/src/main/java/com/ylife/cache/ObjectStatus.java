package com.ylife.cache;

/**
 * Created by InThEnd on 2017/1/4.
 * 根据当前正在进行的操作状态可以推导出{@link CacheObject}的状态。
 */
public enum ObjectStatus {

    /**
     * 一致态。cache的基本的状态，和数据库中数据保持一致性。
     */
    COINCIDENT,
    /**
     * 可控态。当数据为一致态且此时只有一个线程操作的时候，数据转变为可控态。
     */
    CONTROLLABLE,
    /**
     * 不确定态。当多个操作交叉执行的时候，多个操作均完成后的结果是无法保证一致性的。此时呈不确定态。
     */
    UNCERTAIN
}
