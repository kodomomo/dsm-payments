package com.github.kodomo.dsmpayments.infra.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DMSUserEntity {
    private String id;
    private String password;
}
