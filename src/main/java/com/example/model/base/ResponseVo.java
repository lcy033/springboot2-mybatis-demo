package com.example.model.base;

import com.example.enums.ResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

@ApiModel("响应结果")
public class ResponseVo<T> implements Serializable {

    @ApiModelProperty("返回码")
    private String code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回结果")
    private T result;

    public ResponseVo() {
    }

    public ResponseVo(String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> ResponseVo<T> ofError(String code, String message) {
        return new ResponseVo<>(code, message, null);
    }

    public static <T> ResponseVo<T> ofError(String message) {
        return new ResponseVo<>("500", message, null);
    }

    public static <T> ResponseVo<T> ofError(ResponseCode responseCode) {
        return new ResponseVo<>(responseCode.getValue(), responseCode.getMessage(), null);
    }

    public static <T> ResponseVo<T> ofError() {
        return ofError(ResponseCode.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseVo<T> ofSuccess(T result) {
        return new ResponseVo<>(ResponseCode.OK.getValue(), ResponseCode.OK.getMessage(), result);
    }

    public static <T> ResponseVo<T> ofSuccess() {
        return new ResponseVo<>(ResponseCode.OK.getValue(), ResponseCode.OK.getMessage(), null);
    }

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
