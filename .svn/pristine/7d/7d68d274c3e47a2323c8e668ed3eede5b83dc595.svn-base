package com.ylife.chinapost.controller;

import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.exception.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 */
@ControllerAdvice
public class ControllerExceptionAdvise {

    @ExceptionHandler(UserNotLoginException.class)
    @ResponseBody
    public String handlerUserNotLoginException(UserNotLoginException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.NOT_LOGIN, ex.getMessage()).toJson();
    }

    @ExceptionHandler(AccessRecordException.class)
    @ResponseBody
    public String handlerAccessRecordException(AccessRecordException ex) {
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
        return JsonResponseBean.getErrorResponse(ErrorCode.USER_OPERATION_ERROR, ex.getMessage(), ex.getExObj()).toJson();
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
    public String handlerAuthorizationException(AuthorizationException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_ERROR, ex.getMessage()).toJson();
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseBody
//    public String handlerIllegalArgumentException(IllegalArgumentException ex) {
//        return JsonResponseBean.getErrorResponse(ErrorCode.ILLEGAL_ARGUMENT, ex.getMessage()).toJson();
//    }

    @ExceptionHandler(UserExistException.class)
    @ResponseBody
    public String handlerUserExistException(UserExistException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.USER_EXIST, ex.getMessage()).toJson();
    }

    @ExceptionHandler()
    @ResponseBody
    public String handlerGrandException(GrandException ex) {
        return JsonResponseBean.getErrorResponse(ErrorCode.NOT_ENOUGH_UCOIN, ex.getExObj().toString()).toJson();
    }

}
