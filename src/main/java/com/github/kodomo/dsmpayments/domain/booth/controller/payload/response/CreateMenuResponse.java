package com.github.kodomo.dsmpayments.domain.booth.controller.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMenuResponse {
    private Integer menuId;

    private String boothId;

    private String name;

    private Integer price;
}
