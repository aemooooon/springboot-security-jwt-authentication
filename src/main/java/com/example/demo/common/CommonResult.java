package com.example.demo.common;

import java.io.Serializable;

public class CommonResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setResultCode(ErrorCodeEnum code) {
        this.code = code.code();
        this.msg = code.message();
    }

    //成功 不返回数据直接返回成功信息
    public static CommonResult success() {
        CommonResult result = new CommonResult();
        result.setResultCode(ErrorCodeEnum.SUCCESS);
        return result;
    }

    //成功 并且加上返回数据
    public static CommonResult success(Object data) {
        CommonResult result = new CommonResult();
        result.setResultCode(ErrorCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    //成功 自定义成功返回状态 加上数据
    public static CommonResult success(ErrorCodeEnum resultCode, Object data) {
        CommonResult result = new CommonResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    // 单返回失败的状态码
    public static CommonResult fail(ErrorCodeEnum resultCode) {
        CommonResult result = new CommonResult();
        result.setResultCode(resultCode);
        return result;
    }

    // 返回失败的状态码及数据
    public static CommonResult fail(ErrorCodeEnum resultCode, Object data) {
        CommonResult result = new CommonResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
}
