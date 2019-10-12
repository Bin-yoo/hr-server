package com.cn.ncvt.result;

/**
 *  @description 响应结果生成工厂类
 */
public class ResultFactory {

    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, false, "成功", data);
    }

    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, true, message, null);
    }

    public static Result buildPermissionFailResult(String message) {
        return buildResult(ResultCode.UNAUTHORIZED,true, message, null);
    }

    public static Result buildResult(ResultCode resultCode, boolean error, String message, Object data) {
        return buildResult(resultCode.code, error, message, data);
    }

    public static Result buildResult(int resultCode, boolean error, String message, Object data) {
        return new Result(resultCode, error, message, data);
    }
}
