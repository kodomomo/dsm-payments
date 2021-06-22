package com.github.kodomo.dsmpayments.domain.booth.controller.payload.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoothLoginResponse {
    private String accessToken;
}
