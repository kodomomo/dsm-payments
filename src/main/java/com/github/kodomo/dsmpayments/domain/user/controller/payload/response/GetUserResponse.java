package com.github.kodomo.dsmpayments.domain.user.controller.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserResponse {
    private String number;

    private String uuid;

    private String name;

    private Integer coin;

}
