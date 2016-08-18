package com.javen.rxmvp.bean;

/*
Http服务返回一个固定格式的数据
{
 "error_code": 0,
 "reason": "Success",
 "result":  {}
}
 */
public class HttpJuHeResult<T> {
    private int error_code;
    private String reason;
    private T result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpJuHeResult{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}