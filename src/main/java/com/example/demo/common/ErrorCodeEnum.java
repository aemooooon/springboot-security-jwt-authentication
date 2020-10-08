package com.example.demo.common;

public enum ErrorCodeEnum {

    SUCCESS(9999, "成功"),
    FAILURE(0000, "失败"),
    ERROR_UNKNOWN(1008, "未知错误"),
    ERROR_PARAM(1009, "参数错误"),
    EMPTY_PARAM(1010, "参数为空"),
    NO_RECORD(1001, "数据库没有记录"),
    FAILED_INSERT(1002, "数据插入失败"),
    FAILED_UPDATE(1003, "数据更新失败"),
    FAILED_DELETE(1004, "数据删除失败"),
    EXISTS_USER_ACCOUNT(1005, "账号已存在"),
    NOT_MATCH_USER_ACCOUNT_PASSWORD(1006, "账号或密码不匹配"),
    NOT_MATCH_CAPTCHA(1007, "验证码错误"),
    NOT_EXISTS_USER_ACCOUNT(1014, "帐号不存在"),
    EMPTY_USER_ACCOUNT(1015, "帐号为空"),
    EMPTY_USER_SECURITY(1016, "密码为空"),
    EMPTY_CAPTCHA_CODE(1017, "验证码为空"),
    NOT_MATCH_USER_ACCOUNT(1018, "帐号格式错误"),
    ERROR_FORMAT_USER_SECURITY(1019, "密码格式错误"),
    NOT_EXISTS_PRIVATE_VERIFICATION(1020,"未设置私密问题"),
    NOT_MATCH_PRIVATE_VERIFICATION(1021, "私密问题验证失败"),
    EMPTY_CIPHER(1022,"私密答案为空"),
    NOT_ENOUGH_PRIVATE_VERIFICATION(1023,"私密问题最少设置3个"),
    EXISTS_QUESTION(1024,"私密问题已存在"),
    EMPTY_QUESTION(1025,"私密问题为空"),
    ERROR_FORMAT_USER_ACCOUNT(1026, "帐号格式错误"),
    FAILED_TOKEN_GENERATED(1027,"JWT 生成失败"),
    NOT_MATCH_JWT(1028,"JWT 验证失败"),
    INVALID_JWT(1029,"无效的 Token"),
    OUTOFDATE_JWT(1030,"Token 已过期");


    private Integer code;
    private String message;

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ErrorCodeEnum item : ErrorCodeEnum.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ErrorCodeEnum item : ErrorCodeEnum.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
