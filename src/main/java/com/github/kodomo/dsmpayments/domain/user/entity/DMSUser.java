package com.github.kodomo.dsmpayments.domain.user.entity;

import com.github.kodomo.dsmpayments.infra.db.DMSUserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DMSUser {
    private String id;

    private String password;

    private String name;

    private Integer number;

    public static Optional<DMSUser> map(DMSUserEntity dmsUserEntity) {
        if (dmsUserEntity == null) return Optional.empty();
        return Optional.of(DMSUser.builder()
                .id(dmsUserEntity.getId())
                .password(dmsUserEntity.getPassword())
                .name(dmsUserEntity.getName())
                .number(dmsUserEntity.getNumber())
                .build());
    }
}
