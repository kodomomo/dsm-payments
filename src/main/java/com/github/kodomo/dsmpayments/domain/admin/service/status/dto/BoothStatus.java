package com.github.kodomo.dsmpayments.domain.admin.service.status.dto;

import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoothStatus {

    private final String id;
    private final String name;
    private final int coin;
    private final int totalCoin;

    public static BoothStatus of(Booth booth) {
        return new BoothStatus(
                booth.getBoothId(),
                booth.getBoothName(),
                booth.getCoin(),
                booth.getTotalCoin()
        );
    }

}
