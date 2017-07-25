package com.ylife.cache.exception;

/**
 * Created by InThEnd on 2017/2/20.
 */
public class ClassDefinitionException extends CacheException {
    public static final String message = "缓存的对象类型错误。";

    public ClassDefinitionException() {
        super(message);
    }

    public ClassDefinitionException(String message) {
        super(message);
    }

    public ClassDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassDefinitionException(Throwable cause) {
        super(cause);
    }

}
