package com.github.kodomo.dsmpayments.domain.receipt.integrate;

import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptPageDTO;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import org.springframework.data.domain.Pageable;

public interface ReceiptIntegrate {
    void registerReceipt(ReceiptDTO receiptDTO);
    ReceiptPageDTO findAll(Pageable pageable);
    ReceiptPageDTO findAllByQuery(String query, Pageable pageable);
    ReceiptPageDTO findAllForUser(User user, Pageable pageable);
    ReceiptPageDTO findAllForSeller(Booth booth, Pageable pageable);
}