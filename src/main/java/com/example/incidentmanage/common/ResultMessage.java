package com.example.incidentmanage.common;

import java.io.Serializable;

public class ResultMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean success;
    private String message;
    private Object data;

    public ResultMessage(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static ResultMessage failure(String message) {
        return new ResultMessage(false, message, null);
    }

    public static ResultMessage success(Object data) {
        return new ResultMessage(true, "", data);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
