package com.ylife.exception;

/**
 * Created by InThEnd on 2016/4/11.
 * <p/>
 * 用户已存在。
 */
public class UserExistException extends RuntimeException {

    private static final String message = "用户已存在。";

    public UserExistException() {
        super(message);
    }

    public UserExistException(String s) {
        super(s);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }

}
