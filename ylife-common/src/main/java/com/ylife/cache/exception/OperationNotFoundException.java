package com.ylife.cache.exception;

/**
 * Created by InThEnd on 2017/3/3.
 */
public class OperationNotFoundException extends CacheException {

    public static final String message = "缓存行为未找到。";

    public OperationNotFoundException() {
        super(message);
    }

    public OperationNotFoundException(String message) {
        super(message);
    }

    public OperationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotFoundException(Throwable cause) {
        super(cause);
    }

}
