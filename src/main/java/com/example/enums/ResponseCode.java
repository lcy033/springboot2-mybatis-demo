package com.example.enums;

import java.util.Objects;

public enum ResponseCode {

    OK("200", "OK"),
    UNAUTHORIZED("401", "Unauthorized"),
    PARAMETER_ERROR("400", "parameter error"),

    INTERNAL_SERVER_ERROR("500", "哎呀，宝宝忙晕了~给个机会，重试下"),
    REPEAT("520", "请勿重复提交");

    private final String value;
    private final String message;

    ResponseCode(String value, String reasonPhrase) {
        this.value = value;
        this.message = reasonPhrase;
    }


    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseCode valueOf(int statusCode) {
        for (ResponseCode responseCode : values()) {
            if (Objects.equals(responseCode.value, statusCode)) {
                return responseCode;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }

}
