package com.github.kodomo.dsmpayments.infra.feign.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class FeignBadRequestException extends GlobalException {

    public FeignBadRequestException() {
        super(400, "Feign Bad Request");
    }
}
