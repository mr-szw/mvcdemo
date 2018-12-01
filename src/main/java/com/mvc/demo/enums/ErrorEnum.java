package com.mvc.demo.enums;

/**
 * @author by Dawei on 2018/8/22.
 * 错误返回值代码
 */
public enum ErrorEnum {

    SUCCESS(0, "成功"),
    ERROR(1, "系统异常"),
    ERROR_PARAM(2, "参数异常"),
    LOGIN_FAIL_USERNAME(3, "用户名不存在"),
    LOGIN_FAIL_PASSWORD(4, "密码错误"),
    LOGIN_FAILED(5, "登陆失败"),

    ;

    private int code;
    private String desc;

    ErrorEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /* 通过Code 返回 msg */
    public static String getDescByCode(int code) {
        for (ErrorEnum errorEnum : values()) {
            if(errorEnum.getCode() == code) {
                return errorEnum.getDesc();
            }
        }
        return "";
    }

}
