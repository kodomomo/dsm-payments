package com.github.kodomo.dsmpayments.infra.feign.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class FeignExpiredTokenException extends GlobalException {

    public FeignExpiredTokenException() {
        super(419, "Feign Expired Token");
    }
}
