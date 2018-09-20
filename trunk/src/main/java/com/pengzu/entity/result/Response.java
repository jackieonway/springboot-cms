package com.pengzu.entity.result;

/**
 * Created by Administrator on 2017/12/3.
 */
public class Response<T> {

    /**
     * 返回码 默认200 成功
     */
    private String code = "200";

    /**
     * 成功返回信息
     */
    private String message = "";

    /**
     * 失败返回信息
     */
    private String errorMessage = "";

    /**
     * 状态 默认 true 成功
     */
    private Boolean isResult = true;

    /**
     * 结果
     */
    private T t;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getResult() {
        return isResult;
    }

    public void setResult(Boolean result) {
        isResult = result;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    /**
     * 默认构造函数
     */
    public Response() {
    }

    /**
     * 成功返回response
     *
     * @param isResult 状态
     * @param message  消息
     * @param t        结果
     * @param code     返回码
     */
    public Response(Boolean isResult, String message, T t, String code) {
        this.isResult = isResult;
        this.message = message;
        this.t = t;
        this.code = code;
    }

    /**
     * 失败返回response
     *
     * @param isResult     状态
     * @param errorMessage 错误信息
     * @param code         返回码
     */
    public Response(Boolean isResult, String errorMessage, String code) {
        this.isResult = isResult;
        this.errorMessage = errorMessage;
        this.code = code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":\"")
                .append(code).append('\"');
        sb.append(",\"message\":\"")
                .append(message).append('\"');
        sb.append(",\"errorMessage\":\"")
                .append(errorMessage).append('\"');
        sb.append(",\"isResult\":")
                .append(isResult);
        sb.append(",\"t\":")
                .append(t);
        sb.append('}');
        return sb.toString();
    }
}
