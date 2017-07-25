package com.ylife.expression.exception;

/**
 * Created by InThEnd on 2016/6/5.
 * 解析异常
 */
public class ParseException extends RuntimeException {

    public static final String message = "解析错误。";

    public ParseException() {
        super(message);
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
