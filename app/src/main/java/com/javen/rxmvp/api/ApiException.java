package com.javen.rxmvp.api;

/**
 * API异常处理
 */
public class ApiException extends RuntimeException{
    public final static  int  TIME_MUST_10=209501;
    public final static  int  TIME_OTHER=209502;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code){
        String message = "";
        switch (code) {
            case TIME_MUST_10:
                message = "必须为10位时间戳";
                break;
            case TIME_OTHER:
                message = "page、pagesize必须为int类型,time为10位时间戳";
                break;
            default:
                message = "未知错误";

        }
        return message;
    }
}