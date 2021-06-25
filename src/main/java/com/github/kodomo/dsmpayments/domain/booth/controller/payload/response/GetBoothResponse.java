package com.github.kodomo.dsmpayments.domain.booth.controller.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetBoothResponse {
    private String id;

    private String name;

    private Integer coin;

    private Integer totalCoin;
}
