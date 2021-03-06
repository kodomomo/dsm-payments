package com.github.kodomo.dsmpayments.domain.user.controller.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String number;

    private String name;

    private String uuid;

    private Integer coin;

    private Integer countOfUsedBooth;
}
