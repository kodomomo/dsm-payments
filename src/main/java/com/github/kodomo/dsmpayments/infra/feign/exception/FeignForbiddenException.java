package com.github.kodomo.dsmpayments.infra.feign.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class FeignForbiddenException extends GlobalException {

    public FeignForbiddenException() {
        super(403, "Feign Forbidden");
    }
}
