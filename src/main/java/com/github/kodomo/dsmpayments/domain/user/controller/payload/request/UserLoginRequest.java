package com.github.kodomo.dsmpayments.domain.user.controller.payload.request;

import lombok.Getter;

@Getter
public class UserLoginRequest {
    private String accountId;

    private String password;
}
