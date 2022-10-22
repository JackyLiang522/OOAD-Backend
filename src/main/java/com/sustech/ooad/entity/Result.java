package com.sustech.ooad.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private int code;
    private Object data;

    public Result(int code) {
        this.code = code;
    }
}
