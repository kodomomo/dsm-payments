package com.github.kodomo.dsmpayments.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class XquareUser {

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
