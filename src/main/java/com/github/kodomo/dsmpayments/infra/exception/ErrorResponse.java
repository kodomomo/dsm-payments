package com.github.kodomo.dsmpayments.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int statusCode;
    private final String message;
}
