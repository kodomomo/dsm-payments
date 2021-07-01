package com.github.kodomo.dsmpayments.domain.admin.service.status.dto;

import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserStatus {

    private final String uuid;
    private final String number;
    private final String name;
    private final int coin;

    public static UserStatus of(User user) {
        return new UserStatus(
                user.getUserUuid(),
                user.getUserNumber(),
                user.getUserName(),
                user.getCoin()
        );
    }

}
