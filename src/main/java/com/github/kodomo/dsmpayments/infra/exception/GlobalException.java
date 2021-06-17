package com.github.kodomo.dsmpayments.infra.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private final int statusCode;
    private final String message;

    public GlobalException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

}
