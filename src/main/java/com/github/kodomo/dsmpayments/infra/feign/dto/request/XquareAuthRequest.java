package com.github.kodomo.dsmpayments.infra.feign.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
public class XquareAuthRequest {

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
