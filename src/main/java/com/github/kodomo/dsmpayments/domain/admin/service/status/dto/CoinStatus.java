package com.github.kodomo.dsmpayments.domain.admin.service.status.dto;

import com.github.kodomo.dsmpayments.domain.receipt.service.dto.BoothDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CoinStatus {
    private final double allBoothCoinAverage;
    private final double allUserCoinAverage;
    private final List<Long> userCoinUseOfHour;
    private final List<Long> boothCoinIncomeOfHour;
    private final List<Long> boothCoinExpensesOfHour;
    private final List<BoothDTO> totalCoinOfBooths;
}
