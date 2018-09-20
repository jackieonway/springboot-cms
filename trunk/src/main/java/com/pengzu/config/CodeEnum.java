package com.pengzu.config;

public enum CodeEnum {

    /**
     * 通用成功返回信息
     */
    SUCCESS("成功"),
    /**
     * 通用失败返回信息
     */
    FAIL("失败"),
    /**
     * 验证码session属性key
     */
    VERIFY_CODE_ID("verifyCode"),
    /**
     * 验证码为空
     */
    VERIFY_CODE_IS_NULL("验证码为空"),
    /**
     * 验证码错误
     */
    VERIFY_CODE_ERROR("验证码错误"),
    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR("用户名或密码错误"),

    /**
     * 用户被禁用
     */
    USER_FORBIDDEN("用户被禁用"),


    /**
     * 联系方式格式错误
     */
    PHONE_ERROR("联系方式格式错误"),

    /**
     * 邮箱格式错误
     */
    EMAIL_ERROR("邮箱格式错误"),

    /**
     * 系统用户Session属性key
     */
    SYS_USER_SESSION_ID("sysuser"),
    /**
     * 通用成功返回编码
     */
    SUCCESS_CODE("200"),
    /**
     * 通用失败返回编码
     */
    FAIL_CODE("500"),

    /**
     * 新增角色失败
     */
    ADD_ROLE_FAIL("新增角色失败"),

    /**
     * 修改角色失败
     */
    UPDATE_ROLE_FAIL("修改角色失败");

    private String value;

    CodeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
