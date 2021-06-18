package com.github.kodomo.dsmpayments.domain.user.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class LoginFailedException extends GlobalException {
    public LoginFailedException() { super(403, "Login Failed, Check your id or password."); }
}
