package com.github.kodomo.dsmpayments.domain.admin.service.status;

import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.BoothStatus;
import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.CoinStatus;
import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.UserStatus;
import com.github.kodomo.dsmpayments.domain.booth.repository.BoothRepository;
import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.BoothDTO;
import com.github.kodomo.dsmpayments.domain.user.repository.DsmPaymentsUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminStatusServiceImpl implements AdminStatusService {

    private final DsmPaymentsUserRepository userRepository;
    private final BoothRepository boothRepository;
    private final ReceiptIntegrate receiptIntegrate;

    @Override
    public CoinStatus getAllStatus() {
        Double allBoothCoinAverage = boothRepository.allBoothCoinAverage();
        if (allBoothCoinAverage == null) allBoothCoinAverage = 0.0;
        Double allUserCoinAverage = userRepository.allUserCoinAverage();
        if (allUserCoinAverage == null) allUserCoinAverage = 0.0;
        List<Long> userCoinUseOfHour = receiptIntegrate.userCoinUseOfHour();
        List<Long> boothCoinIncomeOfHour = receiptIntegrate.boothCoinIncomeOfHour();
        if (boothCoinIncomeOfHour.size() < 10) boothCoinIncomeOfHour = null;
        List<Long> boothCoinExpensesOfHour = receiptIntegrate.boothCoinExpensesOfHour();
        if (boothCoinExpensesOfHour.size() < 10) boothCoinExpensesOfHour = null;
        List<BoothDTO> totalCoinOfBooths = boothRepository.findAll()
                .stream().map(BoothDTO::of)
                .collect(Collectors.toList());

        return new CoinStatus(
                allBoothCoinAverage, allUserCoinAverage, userCoinUseOfHour,
                boothCoinIncomeOfHour, boothCoinExpensesOfHour, totalCoinOfBooths
        );
    }

    @Override
    public List<UserStatus> findAllUserStatus(String searchWord) {
        return userRepository.findAllUserStatus(searchWord)
                .stream().map(UserStatus::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<BoothStatus> findAllBoothStatus(String searchWord) {
        return boothRepository.findAllBoothStatus(searchWord)
                .stream().map(BoothStatus::of)
                .collect(Collectors.toList());
    }

}
