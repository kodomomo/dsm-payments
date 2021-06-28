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
    private long id;

    private Integer userNumber;

    private String userName;

    private String boothName;

    private ReceiptSender sender;

    private int requestedValue;

    private int tax;

    private int finalValue;

    private LocalDateTime createAt;

    public static ReceiptVO of(ReceiptDTO receiptDTO) {
        return new ReceiptVO(
                receiptDTO.getId(),
                receiptDTO.getUser().getUserNumber(),
                receiptDTO.getUser().getUserName(),
                receiptDTO.getBooth().getBoothName(),
                receiptDTO.getSender(),
                receiptDTO.getRequestValue(),
                receiptDTO.getTax(),
                receiptDTO.getFinalValue(),
                receiptDTO.getCreatedAt()
        );
    }
}
