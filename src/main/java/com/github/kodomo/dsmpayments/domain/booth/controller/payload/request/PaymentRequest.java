package com.github.kodomo.dsmpayments.domain.booth.controller.payload.request;

import lombok.Getter;

@Getter
public class PaymentRequest {
    private Integer menuId;

    private String userUuid;
}
