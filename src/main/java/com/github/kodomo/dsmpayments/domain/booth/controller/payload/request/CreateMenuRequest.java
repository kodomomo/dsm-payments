package com.github.kodomo.dsmpayments.domain.booth.controller.payload.request;

import lombok.Getter;

@Getter
public class CreateMenuRequest {
    private String name;

    private Integer price;
}
