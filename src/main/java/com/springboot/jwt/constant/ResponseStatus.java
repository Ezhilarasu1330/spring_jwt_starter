package com.springboot.jwt.constant;

public enum ResponseStatus {

    SUCCESS(MessageConstants.SUCCESS_CODE, MessageConstants.SUCCESS),
    UNKNOWN_ERROR(MessageConstants.UNKNOWN_ERROR_CODE, MessageConstants.UNKNOWN_ERROR),

    BAD_CREDENTIALS(MessageConstants.ACCESS_FORBIDDEN_CODE, MessageConstants.BAD_CREDENTIALS),
    FORBIDDEN_ACCESS(MessageConstants.ACCESS_FORBIDDEN_CODE, MessageConstants.FORBIDDEN_ACCESS),
    ACC_DISABLED(MessageConstants.ACCESS_FORBIDDEN_CODE, MessageConstants.ACC_DISABLED),
    TOKEN_EXPIRED(MessageConstants.ACCESS_FORBIDDEN_CODE, MessageConstants.TOKEN_EXPIRED),
    USER_FIRST_NAME_NULL(MessageConstants.VALIDATION_CODE, MessageConstants.USER_FIRST_NAME_NULL),
    USER_LAST_NAME_NULL(MessageConstants.VALIDATION_CODE, MessageConstants.USER_LAST_NAME_NULL),
    USER_MAIL_ID_NULL(MessageConstants.VALIDATION_CODE, MessageConstants.USER_MAIL_ID_NULL),
    USER_PASSWORD_NULL(MessageConstants.VALIDATION_CODE, MessageConstants.USER_PASSWORD_NULL),
    USER_PHONE_NUMBER_NULL(MessageConstants.VALIDATION_CODE, MessageConstants.USER_PHONE_NUMBER_NULL),
    USER_MAIL_ID_NOT_VALID(MessageConstants.VALIDATION_CODE, MessageConstants.USER_MAIL_ID_NOT_VALID),
    USER_PHONE_NUMBER_NOT_VALID(MessageConstants.VALIDATION_CODE, MessageConstants.USER_PHONE_NUMBER_NOT_VALID),
    USER_PASSWORD_WEAK(MessageConstants.VALIDATION_CODE, MessageConstants.USER_PASSWORD_WEAK),
    USER_MAIL_ID_EXISTS(MessageConstants.VALIDATION_CODE, MessageConstants.USER_MAIL_ID_EXISTS),
    USER_NAME_NOT_EXISTS(MessageConstants.VALIDATION_CODE, MessageConstants.USER_NAME_NOT_EXISTS),
    INVALID_USER_ID(MessageConstants.VALIDATION_CODE, MessageConstants.INVALID_USER_ID),
    INVALID_REQUEST(MessageConstants.VALIDATION_CODE, MessageConstants.INVALID_REQUEST),
    DATE_PARSE_ERR(MessageConstants.PARSE_ERROR_CODE, MessageConstants.DATE_PARSE_ERR);

    private int code;
    private String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
