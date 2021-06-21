package com.github.kodomo.dsmpayments.domain.user.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class HashException extends GlobalException {
    public HashException() { super(500, "Hash Error"); }
}
