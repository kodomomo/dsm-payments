package com.github.kodomo.dsmpayments.infra.feign.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class FeignException extends GlobalException {

    public FeignException(Integer status, String detail) {
        super(status, detail);
    }
}
