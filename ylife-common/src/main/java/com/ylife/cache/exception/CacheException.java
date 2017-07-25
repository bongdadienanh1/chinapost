package com.ylife.cache.exception;

/**
 * Created by InThEnd on 2017/2/24.
 */
public class CacheException extends RuntimeException {

    public static final String message = "缓存异常。";

    public CacheException() {
        super(message);
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }

}
