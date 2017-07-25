package com.ylife.chinapost.mobile.controller;

import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.exception.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 */
@ControllerAdvice
@RequestMapping(value = "/", produces = "application/json;charset=utf-8")
public class ControllerExceptionAdvise {

    @ExceptionHandler(UserNotLoginException.class)
    @ResponseBody
    public String handlerUserNotLoginException(UserNotLoginException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.NOT_LOGIN, ex.getMessage()).toJson();
    }

    @ExceptionHandler(AccessRecordException.class)
    @ResponseBody
    public String handlerAcessRecordException(AccessRecordException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.ACCESS_DENIED, ex.getMessage()).toJson();
    }

    @ExceptionHandler(ServerInternalException.class)
    @ResponseBody
    public String handlerServerInternalException(ServerInternalException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.SERVER_ERROR, ex.getMessage()).toJson();
    }

    @ExceptionHandler(ServerIOException.class)
    @ResponseBody
    public String handlerServerIOException(ServerIOException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.SERVER_ERROR, ex.getMessage()).toJson();
    }

    @ExceptionHandler(UserOperationException.class)
    @ResponseBody
    public String handlerUserOperationException(UserOperationException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.USER_OPERATION_ERROR, ex.getMessage()).toJson();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public String handlerDuplicateKeyException(DuplicateKeyException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.USER_EXIST, ex.getMessage()).toJson();
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseBody
    public String handlerRecordNotFoundException(RecordNotFoundException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.NOT_FOUND, ex.getMessage()).toJson();
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public String handlerAuthoritionException(AuthorizationException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.DATA_NOT_FOUND, ex.getMessage()).toJson();
    }
}
