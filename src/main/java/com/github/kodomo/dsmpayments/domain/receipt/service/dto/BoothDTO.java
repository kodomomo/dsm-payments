package com.github.kodomo.dsmpayments.domain.receipt.service.dto;

import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoothDTO {

    private final String id;
    private final String name;
    private final int coin;
    private final int totalCoin;

    public static BoothDTO of(Booth booth) {
        if (booth == null) return null;
        return new BoothDTO(
                booth.getBoothId(),
                booth.getBoothName(),
                booth.getCoin(),
                booth.getTotalCoin()
        );
    }

}
