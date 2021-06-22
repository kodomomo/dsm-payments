package com.github.kodomo.dsmpayments.domain.booth.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class InvalidRequestException extends GlobalException {
    public InvalidRequestException() { super(403, "The server is rejecting the request"); }
}
