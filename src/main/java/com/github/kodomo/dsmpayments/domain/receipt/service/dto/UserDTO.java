package com.github.kodomo.dsmpayments.domain.receipt.service.dto;

import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDTO {

    private final String number;
    private final String name;
    private final int coin;
    private final String uuid;

    public static UserDTO of(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getUserNumber(),
                user.getUserName(),
                user.getCoin(),
                user.getUserUuid()
        );
    }

}
