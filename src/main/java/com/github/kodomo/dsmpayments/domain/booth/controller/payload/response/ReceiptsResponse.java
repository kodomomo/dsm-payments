package com.github.kodomo.dsmpayments.domain.booth.controller.payload.response;

import com.github.kodomo.dsmpayments.domain.receipt.entity.Receipt;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptPageDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReceiptsResponse {
    private int totalPage;
    private List<ReceiptVO> receipts;

    public static ReceiptsResponse of(ReceiptPageDTO receipts) {
        List<ReceiptVO> receiptVOs = new java.util.ArrayList<>(Collections.emptyList());

        for (ReceiptDTO receiptDTO: receipts.getReceipts()) {
            receiptVOs.add(ReceiptVO.of(receiptDTO));
        }
        return new ReceiptsResponse(
                receipts.getTotalPage(),
                receiptVOs
        );
    }
}
