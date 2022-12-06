package com.assessment.assessment.exception;

public class ErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

    public ErrorResponse(){

    }

    public ErrorResponse(int status, String message, long timeeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeeStamp) {
        this.timeStamp = timeeStamp;
    }
}
