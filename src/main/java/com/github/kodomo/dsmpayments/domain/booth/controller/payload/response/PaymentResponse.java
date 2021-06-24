package com.github.kodomo.dsmpayments.domain.booth.controller.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResponse {
    private String boothId;

    private String userUuid;

    private Integer requestedCoin;

    private Integer tax;

    private Integer finalCoin;
}
