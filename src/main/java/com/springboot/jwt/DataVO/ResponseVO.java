package com.springboot.jwt.DataVO;

import java.util.Date;

public class ResponseVO {

    private int code;
    private String status;
    private String message;
    private Date date;
    private BaseVO response;

    public ResponseVO() {
        final Date currentDate = new Date();
        this.date = currentDate;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseVO getResponse() {
        return response;
    }

    public void setResponse(BaseVO response) {
        this.response = response;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
