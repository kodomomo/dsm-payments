package com.github.kodomo.dsmpayments.domain.receipt.service.dto;

import com.github.kodomo.dsmpayments.domain.receipt.entity.Receipt;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReceiptPageDTO {

    private final int totalPage;
    private final List<ReceiptDTO> receipts;

    public static ReceiptPageDTO of(Page<Receipt> receipts) {
        int totalPage = receipts.getTotalPages();
        Page<ReceiptDTO> receiptDTOPage = receipts.map(ReceiptDTO::of);
        List<ReceiptDTO> receiptResponses = receiptDTOPage.toList();

        return new ReceiptPageDTO(totalPage, receiptResponses);
    }

}
