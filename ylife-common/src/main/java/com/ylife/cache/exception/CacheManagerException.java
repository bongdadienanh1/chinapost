package com.ylife.cache.exception;

/**
 * Created by InThEnd on 2017/3/3.
 */
public class CacheManagerException extends CacheException {

    public static final String message = "缓存管理器异常。";

    public CacheManagerException() {
        super(message);
    }

    public CacheManagerException(String message) {
        super(message);
    }

    public CacheManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheManagerException(Throwable cause) {
        super(cause);
    }

}
