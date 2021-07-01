package com.github.kodomo.dsmpayments.domain.receipt.integrate;

import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptPageDTO;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReceiptIntegrate {
    ReceiptDTO registerReceipt(ReceiptDTO receiptDTO);
    ReceiptPageDTO findAllByQuery(String query, Pageable pageable);
    List<ReceiptDTO> findAllForUser(User user);
    ReceiptPageDTO findAllForBooth(Booth booth, Pageable pageable);
    Integer getNumOfBoothsUsedByUser(User user);
    Integer getNumOfUsersUsingBooth(Booth booth);
    List<Long> userCoinUseOfHour();
    List<Long> boothCoinIncomeOfHour();
    List<Long> boothCoinExpensesOfHour();
}
