package com.github.kodomo.dsmpayments.domain.receipt.service.dto;

import com.github.kodomo.dsmpayments.domain.receipt.entity.Receipt;
import com.github.kodomo.dsmpayments.domain.receipt.entity.ReceiptSender;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReceiptDTO {

    private final Long id;
    private final User user;
    private final Booth booth;
    private final int requestValue;
    private final Integer tax;
    private final Integer finalValue;
    private final ReceiptSender sender;
    private final LocalDateTime createdAt;

    public ReceiptDTO(User user, Booth booth, int requestValue, ReceiptSender sender) {
        this.id = null;
        this.user = user;
        this.booth = booth;
        this.requestValue = requestValue;
        this.sender = sender;
        this.tax = null;
        this.finalValue = null;
        this.createdAt = null;
    }

    public static ReceiptDTO of(Receipt receipt) {
       return new ReceiptDTO(
               receipt.getId(),
               receipt.getUser(),
               receipt.getBooth(),
               receipt.getRequestedValue(),
               receipt.getTax(),
               receipt.getFinalValue(),
               receipt.getSender(),
               receipt.getCreatedAt()
       );
    }

}
