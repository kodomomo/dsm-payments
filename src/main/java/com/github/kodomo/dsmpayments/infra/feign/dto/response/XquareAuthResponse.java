package com.github.kodomo.dsmpayments.infra.feign.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
public class XquareAuthResponse {

    private String name;

    private UUID id;

    private Date birthDay;

    private Integer grade;

    private Integer classNum;

    private String profileImageUrl;

    private String password;

    private String accountId;

    private Integer num;
}
