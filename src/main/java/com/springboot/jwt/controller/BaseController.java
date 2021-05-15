package com.springboot.jwt.controller;

import com.springboot.jwt.DataVO.BaseVO;
import com.springboot.jwt.DataVO.ResponseVO;
import com.springboot.jwt.constant.MessageConstants;
import com.springboot.jwt.constant.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public abstract class BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    public void success(final ResponseVO responseVO, final BaseVO response, final String message) {
        responseVO.setStatus(MessageConstants.SUCCESS);
        responseVO.setCode(ResponseStatus.SUCCESS.getCode());
        responseVO.setMessage(message);
        responseVO.setResponse(response);
    }

    public void error(final ResponseVO responseVO, final int code, final String message, final Throwable t) {
        responseVO.setStatus(MessageConstants.ERROR);
        responseVO.setCode(code);
        responseVO.setMessage(message);
        if (message.equals(MessageConstants.UNKNOWN_ERROR)) {
            this.LOGGER.error("Code = " + code + ", Message = " + message + " at Date = " + new Date(), t);
        }
    }

    public void userErrorHandler(final ResponseVO responseVO, final Throwable t) {
        switch (t.getMessage()) {
            case MessageConstants.USER_FIRST_NAME_NULL:
                this.error(responseVO, ResponseStatus.USER_FIRST_NAME_NULL.getCode(), ResponseStatus.USER_FIRST_NAME_NULL.getMessage(), t);
                break;
            case MessageConstants.USER_LAST_NAME_NULL:
                this.error(responseVO, ResponseStatus.USER_LAST_NAME_NULL.getCode(), ResponseStatus.USER_LAST_NAME_NULL.getMessage(), t);
                break;
            case MessageConstants.USER_MAIL_ID_NULL:
                this.error(responseVO, ResponseStatus.USER_MAIL_ID_NULL.getCode(), ResponseStatus.USER_MAIL_ID_NULL.getMessage(), t);
                break;
            case MessageConstants.USER_PASSWORD_NULL:
                this.error(responseVO, ResponseStatus.USER_PASSWORD_NULL.getCode(), ResponseStatus.USER_PASSWORD_NULL.getMessage(), t);
                break;
            case MessageConstants.USER_PHONE_NUMBER_NULL:
                this.error(responseVO, ResponseStatus.USER_PHONE_NUMBER_NULL.getCode(), ResponseStatus.USER_PHONE_NUMBER_NULL.getMessage(), t);
                break;
            case MessageConstants.USER_MAIL_ID_NOT_VALID:
                this.error(responseVO, ResponseStatus.USER_MAIL_ID_NOT_VALID.getCode(), ResponseStatus.USER_MAIL_ID_NOT_VALID.getMessage(), t);
                break;
            case MessageConstants.USER_PASSWORD_WEAK:
                this.error(responseVO, ResponseStatus.USER_PASSWORD_WEAK.getCode(), ResponseStatus.USER_PASSWORD_WEAK.getMessage(), t);
                break;
            case MessageConstants.USER_NAME_NOT_EXISTS:
                this.error(responseVO, ResponseStatus.USER_NAME_NOT_EXISTS.getCode(), ResponseStatus.USER_NAME_NOT_EXISTS.getMessage(), t);
                break;
            case MessageConstants.INVALID_USER_ID:
                this.error(responseVO, ResponseStatus.INVALID_USER_ID.getCode(), ResponseStatus.INVALID_USER_ID.getMessage(), t);
                break;
            case MessageConstants.INVALID_REQUEST:
                this.error(responseVO, ResponseStatus.INVALID_REQUEST.getCode(), ResponseStatus.INVALID_REQUEST.getMessage(), t);
                break;
            case MessageConstants.ACC_DISABLED:
                this.error(responseVO, ResponseStatus.ACC_DISABLED.getCode(), ResponseStatus.ACC_DISABLED.getMessage(), t);
                break;
            default:
                this.error(responseVO, ResponseStatus.UNKNOWN_ERROR.getCode(), ResponseStatus.UNKNOWN_ERROR.getMessage(), t);
        }
    }
}
