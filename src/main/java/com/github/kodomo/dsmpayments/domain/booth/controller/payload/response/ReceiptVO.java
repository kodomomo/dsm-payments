package com.github.kodomo.dsmpayments.domain.booth.controller.payload.response;

import com.github.kodomo.dsmpayments.domain.receipt.entity.ReceiptSender;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReceiptVO {
    private final long id;

    private final Integer userNumber;

    private final String userName;

    private final String boothName;

    private final ReceiptSender sender;

    private final int requestedValue;

    private final int tax;

    private final int finalValue;

    private final LocalDateTime createAt;

    public static ReceiptVO of(ReceiptDTO receiptDTO) {
        return new ReceiptVO(
                receiptDTO.getId(),
                receiptDTO.getUserEntity().getUserNumber(),
                receiptDTO.getUserEntity().getUserName(),
                receiptDTO.getBoothEntity().getBoothName(),
                receiptDTO.getSender(),
                receiptDTO.getRequestValue(),
                receiptDTO.getTax(),
                receiptDTO.getFinalValue(),
                receiptDTO.getCreatedAt()
        );
    }
}
