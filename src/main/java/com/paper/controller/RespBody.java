package com.paper.controller;


/*
* 存储response body数据的类
* */
public class RespBody {

    private int statusCode;
    private String message;
    private Object data;

    public RespBody(RespCode respCode) {
        this.statusCode = respCode.getCode();
        this.message = respCode.getMsg();
    }

    public RespBody(RespCode respCode, Object data) {
        this(respCode);
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
/*
* response code 枚举
* */
enum RespCode {

    SUCCESS(0, "成功"), WARN(-1, "错误");

    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}