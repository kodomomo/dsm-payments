package com.github.kodomo.dsmpayments.domain.user.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class SqlErrorException extends GlobalException {
    public SqlErrorException() { super(500, "SQL Error in User Domain"); }
}
