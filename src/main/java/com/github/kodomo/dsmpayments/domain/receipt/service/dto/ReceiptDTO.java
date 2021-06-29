package com.github.kodomo.dsmpayments.domain.receipt.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.kodomo.dsmpayments.domain.receipt.entity.Receipt;
import com.github.kodomo.dsmpayments.domain.receipt.entity.ReceiptSender;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReceiptDTO {

    @JsonIgnore
    private final User userEntity;

    @JsonIgnore
    private final Booth boothEntity;

    private final Long id;
    private final UserDTO user;
    private final BoothDTO booth;
    private final int requestValue;
    private final Integer tax;
    private final Integer finalValue;
    private final ReceiptSender sender;

    public ReceiptDTO(User user, Booth booth, int requestValue, ReceiptSender sender) {
        this.id = null;
        this.userEntity = user;
        this.boothEntity = booth;
        this.user = UserDTO.of(user);
        this.booth = BoothDTO.of(booth);
        this.requestValue = requestValue;
        this.sender = sender;
        this.tax = null;
        this.finalValue = null;
    }

    public static ReceiptDTO of(Receipt receipt) {
       return new ReceiptDTO(
               receipt.getUser(),
               receipt.getBooth(),
               receipt.getId(),
               UserDTO.of(receipt.getUser()),
               BoothDTO.of(receipt.getBooth()),
               receipt.getRequestedValue(),
               receipt.getTax(),
               receipt.getFinalValue(),
               receipt.getSender()
       );
    }

}
