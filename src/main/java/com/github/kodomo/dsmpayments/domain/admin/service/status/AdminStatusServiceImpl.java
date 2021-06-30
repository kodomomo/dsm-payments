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
        double allBoothCoinAverage = boothRepository.allBoothCoinAverage();
        double allUserCoinAverage = userRepository.allUserCoinAverage();
        List<Long> userCoinUseOfHour = receiptIntegrate.userCoinUseOfHour();
        List<Long> boothCoinIncomeOfHour = receiptIntegrate.boothCoinIncomeOfHour();
        List<Long> boothCoinExpensesOfHour = receiptIntegrate.boothCoinExpensesOfHour();
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
