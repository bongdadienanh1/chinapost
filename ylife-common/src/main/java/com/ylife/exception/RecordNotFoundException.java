package com.ylife.exception;

/**
 * Created by InThEnd on 2016/4/16.
 */
public class RecordNotFoundException extends RuntimeException {

    public static final String message = "服务器内部错误。";

    public RecordNotFoundException() {
        super(message);
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }
}
