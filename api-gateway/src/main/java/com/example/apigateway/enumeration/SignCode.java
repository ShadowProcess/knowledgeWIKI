package com.example.apigateway.enumeration;

/**
 * 鉴权，状态码
 *
 * @author admin
 */
public enum SignCode {

    SUCCESS(0, "ok"),

    ACCESSKEY_EMPTY(20001, "[accessKeySecret] is empty"),
    NONCE_EMPTY(20002, "[nonce] is empty"),
    TIMESTAMP_EMPTY(20003, "[timestamp] is empty"),
    SIGNATURE_EMPTY(20004, "[signature] is empty"),

    ACCESSKEY_ERROR(20005, "[accessKeySecret] does not exist"),
    TIMESTAMP_ERROR(20006, "[timestamp] is wrong"),
    VALIDATESIGNATUREERROR(20007, "[signature] verification error"),

    METHOD_NAME_NOT_EXIST(20008, "Method name does not exist"),
    TIMESTAMP_OUTOFDATE_ERROR(20009, "The timestamp is valid for 5 minutes and has expired"),
    NONCE_ERROR(20011, "[nonce] length requires eight digits"),
    NONCE_EXIST(20012, "[nonce] repeat"),
    ;
    private int code;
    private String msg;

    SignCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }
}
