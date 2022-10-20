package com.sustech.ooad.entity;

import lombok.Data;
import lombok.Getter;

@Getter
public class Result {
    private int code;
    private Object data;

    public Result(int code) {
        this.code = code;
    }
}
