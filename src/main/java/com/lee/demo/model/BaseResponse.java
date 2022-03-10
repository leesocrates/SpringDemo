package com.lee.demo.model;

import com.lee.demo.constants.ResponseCode;

public class BaseResponse<E> {
    private String code;
    private String message;
    private E data;

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(ResponseCode.SUCCESS);
        response.setData(data);
        return response;
    }

    public static <T> BaseResponse<T> fail(String msg, String errorCode) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(errorCode);
        response.setMessage(msg);
        return response;
    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
