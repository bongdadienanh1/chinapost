package com.ylife.exception;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/31.
 */
public class GrandException extends RuntimeException {


    private Object exObj;

    public Object getExObj() {
        return exObj;
    }

    public void setExObj(Object exObj) {
        this.exObj = exObj;
    }

    public GrandException() {
        super();
    }

    protected GrandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GrandException(Throwable cause) {
        super(cause);
    }

    public GrandException(String message, Throwable cause) {
        super(message, cause);
    }

    public GrandException(String message) {
        super(message);
    }



    public GrandException(Object exObj){
        this.exObj=exObj;
    }

}
