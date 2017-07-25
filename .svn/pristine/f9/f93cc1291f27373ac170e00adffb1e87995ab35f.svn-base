package com.ylife.cache;

import com.ylife.utils.Assert;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by InThEnd on 2017/1/4.
 * 缓存包装对象。
 */
public class CacheObject implements Serializable {

    private ObjectStatus status = ObjectStatus.COINCIDENT;

    private int updateTimes = 0;

    private long firstEditTime = 0;

    private ValueWrapper valueWrapper;

    private Set<CurrentOperation> operations = new LinkedHashSet<>();

    private CacheObject() {
    }


    //==================================METHOD===================================

    public void setValue(Object value) {
        valueWrapper = new ValueWrapper(value);
    }

    public static CacheObject virtualObject() {
        CacheObject object = new CacheObject();
        object.setStatus(ObjectStatus.COINCIDENT);
        object.valueWrapper = null;
        return object;
    }

    public static CacheObject normalObject(Object object) {
        CacheObject object1 = new CacheObject();
        object1.setStatus(ObjectStatus.COINCIDENT);
        object1.setValue(object);
        return object1;
    }

    public void operationBegin(CurrentOperation operation) {
        operations.add(operation);
        //COINCIDENT-->CONTROLLABLE
        if (operations.size() == 1 && status == ObjectStatus.COINCIDENT) {
            status = ObjectStatus.CONTROLLABLE;
        }
        //CONTROLLABLE-->UNCERTAIN
        else if (operations.size() > 1 && status == ObjectStatus.CONTROLLABLE) {
            status = ObjectStatus.UNCERTAIN;
        } else {
            //impossible
        }
    }

    public void operationSuccess(CurrentOperation operation, Object value) {
        Assert.notNull(value);
        //如果不存在，说明此操作因过期被移除。
        if (!operations.contains(operation)) {
            status = ObjectStatus.UNCERTAIN;
        } else {
            operations.remove(operation);
            //CONTROLLABLE-->COINCIDENT
            if (operations.size() == 0 && status == ObjectStatus.CONTROLLABLE) {
                status = ObjectStatus.COINCIDENT;
            }
        }
        setValue(value);
        if (updateTimes == 0) {
            firstEditTime = System.currentTimeMillis();
        }
        updateTimes++;
    }

    public void operationFailure(CurrentOperation operation) {
        if (!operations.contains(operation)) {
            status = ObjectStatus.UNCERTAIN;
        } else {
            operations.remove(operation);
            if (operations.size() == 0 && status == ObjectStatus.CONTROLLABLE) {
                status = ObjectStatus.COINCIDENT;
            }
        }
    }

    public void operationCross(CurrentOperation operation, Object value) {
        operationBegin(operation);
        operationSuccess(operation, value);
    }

    public boolean needRefresh() {
        return operations.isEmpty() && status == ObjectStatus.UNCERTAIN;
    }

    public void refresh(Object value) {
        //UNCERTAIN-->COINCIDENT
        status = ObjectStatus.COINCIDENT;
        setValue(value);
    }

    /**
     * 清除掉死亡的操作线程。
     *
     * @param second 超过多少时间的操作将被移除。
     */
    public void removeDeathOperation(int second) {
        for (CurrentOperation operation : operations) {
            if ((System.currentTimeMillis() - operation.getStartTime()) / 1000 > second) {
                operations.remove(operation);
                status = ObjectStatus.UNCERTAIN;
            }
        }
    }


    //==================================GET/SET===================================


    public ValueWrapper getValueWrapper() {
        return valueWrapper;
    }

    public void setValueWrapper(ValueWrapper valueWrapper) {
        this.valueWrapper = valueWrapper;
    }

    public Set<CurrentOperation> getOperations() {
        return operations;
    }

    public ObjectStatus getStatus() {
        return status;
    }

    public void setStatus(ObjectStatus status) {
        this.status = status;
    }


    public int getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(int updateTimes) {
        this.updateTimes = updateTimes;
    }

    public long getFirstEditTime() {
        return firstEditTime;
    }

    public void setFirstEditTime(long firstEditTime) {
        this.firstEditTime = firstEditTime;
    }

    public static class ValueWrapper implements Serializable {

        private Object value;

        ValueWrapper(Object value) {
            this.value = value;
        }

        public boolean isNullValue() {
            return value == null;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

}
