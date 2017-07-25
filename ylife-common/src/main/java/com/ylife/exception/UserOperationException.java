package com.ylife.exception;

/**
 * Created by InThEnd on 2016/4/11.
 * <p/>
 * 用户操作异常。
 */
public class UserOperationException extends RuntimeException {

    private Object exObj;

    public Object getExObj() {
        return exObj;
    }

    public void setExObj(Object exObj) {
        this.exObj = exObj;
    }



    public static final String message = "用户操作错误。";

    public UserOperationException() {
        super(message);
    }

    public UserOperationException(String message) {
        super(message);
    }

    public UserOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserOperationException(Throwable cause) {
        super(cause);
    }

    public UserOperationException(Object object){
        this.exObj=object;
    }

}
