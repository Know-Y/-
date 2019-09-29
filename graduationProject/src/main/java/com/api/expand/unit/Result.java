package com.api.expand.unit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;

public class Result<T> implements Serializable {
    private int code;
    //("处理结果描述信息")
    private String msg;
    //("请求结果生成时间戳")
    private Instant timestamp;
    //("处理结果数据信息")
    @JsonInclude(Include.NON_NULL)
    private T data;

    public Result() {
        this.timestamp = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorCode errorType) {
        this.code = errorType.val();
        this.msg = errorType.desc();
        this.timestamp = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorCode errorType, T data) {
        this(errorType);
        this.data = data;
    }

    public Result(ErrorCode code, String msg, T data) {
        this(code);
        this.msg = msg;
        this.data = data;
    }

    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result data(T data) {
        this.data = data;
        return this;
    }

    public static Result success(Object data) {
        return new Result(ErrorCode.E0, data);
    }

    public static Result success() {
        return success((Object)null);
    }

    public static Result fail() {
        return new Result(ErrorCode.E1);
    }

    public static Result fail(ErrorCode error, Object data) {
        return new Result(error, data);
    }

    public static Result fail(ErrorCode errorType) {
        return new Result(errorType);
    }

    public static Result fail(Object data) {
        return new Result(ErrorCode.E1, data);
    }

    public static Result code(ErrorCode code) {
        return new Result(code);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == ErrorCode.E0.val();
    }

    @JsonIgnore
    public boolean isFail() {
        return !this.isSuccess();
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public T getData() {
        return this.data;
    }
}
