package com.github.kodomo.dsmpayments.domain.receipt.entity;

import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "tbl_receipt")
public class Receipt {
    private final float percentOfTax = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_number")
    private User user;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Booth booth;

    @Enumerated(EnumType.STRING)
    private ReceiptSender sender;

    private int requestedValue;

    private int tax;

    private int finalValue;

    @CreatedBy
    private LocalDateTime createdAt;

    private Receipt(User user, Booth booth, ReceiptSender sender, int value) {
        this.user = user;
        this.booth = booth;
        this.sender = sender;
        this.requestedValue = value;
        if (this.sender.equals(ReceiptSender.USER) && this.requestedValue < 0) {
            this.tax = Math.round((float) -this.requestedValue * (50 / (float) 100));
            this.finalValue = this.requestedValue + this.tax;
        }
    }

    public static Receipt of(ReceiptDTO receiptDTO) {
        return new Receipt(
                receiptDTO.getUser(),
                receiptDTO.getBooth(),
                receiptDTO.getSender(),
                receiptDTO.getRequestValue()
        );
    }

}
