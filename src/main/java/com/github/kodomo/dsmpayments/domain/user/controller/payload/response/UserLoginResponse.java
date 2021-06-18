package com.github.kodomo.dsmpayments.domain.user.controller.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginResponse {
    private String accessToken;
}
