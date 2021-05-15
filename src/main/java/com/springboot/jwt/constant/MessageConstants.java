package com.springboot.jwt.constant;

public class MessageConstants {

    public static final int SUCCESS_CODE = 200;
    public static final int VALIDATION_CODE = 201;
    public static final int BAD_REQUEST_CODE = 202;
    public static final int ACCESS_FORBIDDEN_CODE = 203;
    public static final int UNKNOWN_ERROR_CODE = 204;
    public static final int IO_ERROR_CODE = 205;
    public static final int PARSE_ERROR_CODE = 206;

    public final static String SUCCESS = "Success";
    public static final String ERROR = "Error";

    public final static String TOKEN_EXPIRED = "The Token is expired, Kindly login again";
    public static final String INVALID_REQUEST = "The given request is invalid, kindly give a valid one";
    public static final String ACC_DISABLED = "Account disabled, Kindly contact administrator";
    public final static String BAD_CREDENTIALS = "The credentials is wrong, Kindly give the valid credentials";
    public final static String FORBIDDEN_ACCESS = "Access forbidden for this request";
    public final static String UNKNOWN_ERROR = "Kindly contact Administrator, There is some unknown error processing this request";

    public final static String USER_FIRST_NAME_NULL = "The First Name is Null, Kindly add first name";
    public final static String USER_LAST_NAME_NULL = "The Last Name is Null, Kindly add last name";
    public final static String USER_MAIL_ID_NULL = "The Mail Id is Null, Kindly add mail Id";
    public final static String USER_PASSWORD_NULL = "The Password is Null, Kindly add password";
    public final static String USER_PHONE_NUMBER_NULL = "The Phone number is Null, Kindly add phone number";
    public final static String USER_MAIL_ID_NOT_VALID = "The Given mail id is not valid, Kindly give valid mail address";
    public final static String USER_PHONE_NUMBER_NOT_VALID = "The Given Phone number is not valid, Kindly give valid phone number";
    public final static String USER_PASSWORD_WEAK = "The given password is weak, The password must contains 8 characters along with 2 numbers and 1 special character";
    public final static String USER_MAIL_ID_EXISTS = "The Given mail id is already exists, Kindly change it";
    public final static String USER_NAME_NOT_EXISTS = "The given credentials is not present, Kindly give a valid one";
    public static final String INVALID_USER_ID = "The given user id is invalid, Kindly give a valid one";

    public final static String USER_REGISTER_SUCCESS = "User registered successfully";
    public final static String USER_AUTHENTICATE_SUCCESS = "User authenticated successfully";

    public static final String DATE_PARSE_ERR = "Error occurred while parsing date, Contact administrator";
}
