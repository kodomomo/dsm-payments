package com.github.kodomo.dsmpayments.domain.user.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException() { super(404, "User Not Found");}
}
