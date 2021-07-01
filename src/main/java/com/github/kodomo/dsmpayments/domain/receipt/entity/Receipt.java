package com.github.kodomo.dsmpayments.domain.receipt.entity;

import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity(name = "tbl_receipt")
public class Receipt {

    private static final int TAX = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_number")
    private User user;

    @ManyToOne
    @JoinColumn(name = "booth_id")
    private Booth booth;

    @Enumerated(EnumType.STRING)
    private ReceiptSender sender;

    private int requestedValue;

    private int tax;

    private int finalValue;

    @CreatedDate
    private Date createdAt;

    private Receipt(User user, Booth booth, ReceiptSender sender, int requestedValue, Date createdAt) {
        this.user = user;
        this.booth = booth;
        this.sender = sender;
        this.requestedValue = requestedValue;
        this.tax = 0;
        if (this.sender.equals(ReceiptSender.USER) && this.requestedValue < 0) {
            this.tax = Math.round((float) this.requestedValue * (TAX / (float) 100));
        }
        this.finalValue = this.requestedValue - this.tax;
        this.createdAt = createdAt;
    }

    public static Receipt of(ReceiptDTO receiptDTO) {
        return new Receipt(
                receiptDTO.getUserEntity(),
                receiptDTO.getBoothEntity(),
                receiptDTO.getSender(),
                receiptDTO.getRequestValue(),
                new Date(System.currentTimeMillis())
        );
    }

}
