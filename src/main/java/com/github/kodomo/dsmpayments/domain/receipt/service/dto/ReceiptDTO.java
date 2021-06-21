package com.github.kodomo.dsmpayments.domain.receipt.service.dto;

import com.github.kodomo.dsmpayments.domain.receipt.entity.Receipt;
import com.github.kodomo.dsmpayments.domain.receipt.entity.ReceiptSender;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReceiptDTO {

    private final Long id;
    private final User user;
    private final Booth booth;
    private final int value;
    private final ReceiptSender sender;

    public static ReceiptDTO of(Receipt receipt) {
       return new ReceiptDTO(
               receipt.getId(),
               receipt.getUser(),
               receipt.getBooth(),
               receipt.getValue(),
               receipt.getSender()
       );
    }

}
