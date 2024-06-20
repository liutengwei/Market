package com.example.springboot1.Controller.Utils;

import lombok.Data;

@Data
public class Result {

    private String msg;
    private Object data;

    public Result(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }


}
