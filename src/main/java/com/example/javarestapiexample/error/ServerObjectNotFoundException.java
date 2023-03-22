package com.example.javarestapiexample.error;

import lombok.Getter;

@Getter
public class ServerObjectNotFoundException extends Exception{
    private final int code;

    public ServerObjectNotFoundException(String exceptionMsg, int code) {
        super(exceptionMsg);
        this.code = code;
    }
}
