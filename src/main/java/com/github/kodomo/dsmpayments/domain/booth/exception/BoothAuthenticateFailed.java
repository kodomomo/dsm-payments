package com.github.kodomo.dsmpayments.domain.booth.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class BoothAuthenticateFailed extends GlobalException {
    public BoothAuthenticateFailed() { super(403, "BoothAuthenticateFailed"); }
}