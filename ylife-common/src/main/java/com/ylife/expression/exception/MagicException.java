package com.ylife.expression.exception;

/**
 * Created by InThEnd on 2016/6/5.
 * 解析异常
 */
public class MagicException extends Exception {

    public static final String message = "转换错误。";

    public MagicException() {
        super(message);
    }

    public MagicException(String message) {
        super(message);
    }

    public MagicException(String message, Throwable cause) {
        super(message, cause);
    }

    public MagicException(Throwable cause) {
        super(cause);
    }
}
