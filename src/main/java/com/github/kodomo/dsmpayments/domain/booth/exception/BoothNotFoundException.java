package com.github.kodomo.dsmpayments.domain.booth.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class BoothNotFoundException extends GlobalException {
    public BoothNotFoundException() { super(404, "Booth Not Found."); }
}
