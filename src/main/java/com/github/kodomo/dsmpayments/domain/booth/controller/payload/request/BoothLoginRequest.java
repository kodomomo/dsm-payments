package com.github.kodomo.dsmpayments.domain.booth.controller.payload.request;

import lombok.Getter;

@Getter
public class BoothLoginRequest {
    private String id;

    private String password;
}
