package com.github.kodomo.dsmpayments.domain.booth.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class UnauthorizedBoothException extends GlobalException {
    public UnauthorizedBoothException() { super(401, "Unauthorized"); }
}