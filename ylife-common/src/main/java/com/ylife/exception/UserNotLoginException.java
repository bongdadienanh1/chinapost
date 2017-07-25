package com.ylife.exception;

/**
 * Created by InThEnd on 2016/4/11.
 * <p/>
 * 用户未登录。
 */
public class UserNotLoginException extends RuntimeException {

    private static final String message = "用户未登录。";

    public UserNotLoginException() {
        super(message);
    }

    public UserNotLoginException(String s) {
        super(s);
    }

    public UserNotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotLoginException(Throwable cause) {
        super(cause);
    }

}
