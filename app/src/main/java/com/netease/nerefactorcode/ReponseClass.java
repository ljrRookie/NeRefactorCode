package com.netease.nerefactorcode;

public class ReponseClass {

    private int error_code;
    private String reason;

    public ReponseClass(int error_code, String reason) {
        this.error_code = error_code;
        this.reason = reason;
    }

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
}
