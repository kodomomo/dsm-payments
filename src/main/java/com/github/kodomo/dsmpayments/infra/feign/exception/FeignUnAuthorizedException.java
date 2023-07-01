package com.github.kodomo.dsmpayments.infra.feign.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class FeignUnAuthorizedException extends GlobalException {

    public FeignUnAuthorizedException() {
        super(401, "Feign UnAuthorized");
    }
}
